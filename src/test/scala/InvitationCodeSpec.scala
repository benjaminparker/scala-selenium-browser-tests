import org.fluentlenium.core.Fluent
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.specs2.mutable._
import org.specs2.specification.Scope

class InvitationCodeSpec extends Specification with Scope {
  val invitationCodeCheck = "https://ppd-ges.notprod.homeoffice.gov.uk/register-to-apply/invitationCodeCheck"

  trait Context extends Scope {
    val driver = new HtmlUnitDriver()
    val browser = new Fluent(driver) {}
    browser.getCookies.clear
    browser.goTo(invitationCodeCheck)
  }

  "Redeem Invitation Code" should {

    "show an error when no code is entered" in new Context {
      browser.click("#updateContinue")
      browser.url must endWith("/invitationCodeError")
    }

    "show an error when an invalid code is entered" in new Context {
      browser.fill("#invitationCode").`with`("NV4L1DC0D3")
      browser.submit("#updateContinue")
      browser.url must endWith("/invitationCodeError")
    }

    "go to start of form when an valid code is entered" in new Context {
      browser.fill("#invitationCode").`with`("SMOKETOKEN")
      browser.submit("#updateContinue")
      browser.url must endWith("/register-to-apply/registration")
    }
  }
}


