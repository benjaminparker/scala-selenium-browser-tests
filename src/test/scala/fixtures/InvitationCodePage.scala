package fixtures

import org.fluentlenium.core.Fluent
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.specs2.matcher.MatchResult
import org.specs2.mutable._
import org.specs2.specification.{AfterEach, Scope}


trait InvitationCodePage extends SpecLike with Scope with AfterEach {
  private val browser = new HtmlUnitDriver()
  val page = new Fluent(browser) {}
  page.getCookies.clear

  def goToInvitationCodePage = page.goTo("http://localhost:3022/register-to-apply/invitationCodeCheck")
  def typeInvitationCode: String => Fluent = code => page.fill("#invitationCode").`with`(code)
  def submitInvitationCode = page.click("#updateContinue")
  def currentPage() = page.url
  def beTheErrorPage(): String => MatchResult[String] = url => url must endWith("/invitationCodeError")
  def beFormStartPage(): String => MatchResult[String] = url => url must endWith("/register-to-apply/registration")

  override def after = browser.close()
}
