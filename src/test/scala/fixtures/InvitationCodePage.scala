package fixtures

import org.fluentlenium.core.Fluent
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.specs2.matcher.MatchResult
import org.specs2.mutable._
import org.specs2.specification.Scope


trait InvitationCodePage extends SpecLike with Scope {
  val page = new Fluent(new HtmlUnitDriver()) {}
  page.getCookies.clear
  page.goTo("http://localhost:3022/register-to-apply/invitationCodeCheck")

  def invitationCode: String => Fluent = code => page.fill("#invitationCode").`with`(code)
  def submit = page.click("#updateContinue")
  def beErrorPageURL: String => MatchResult[String] = url => url must endWith("/invitationCodeError")
  def beFormStartPageURL: String => MatchResult[String] = url => url must endWith("/register-to-apply/registration")
}
