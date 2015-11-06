import org.fluentlenium.core.Fluent
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.specs2.mutable._
import org.specs2.specification.Scope

class InvitationCodeSpec extends Specification with Scope {
  val invitationCodeCheck = "http://localhost:3022/register-to-apply/invitationCodeCheck"

  trait InvitationCodeRedemptionPage extends Scope {
    val browser = new Fluent(new HtmlUnitDriver()) {}
    browser.getCookies.clear
    browser.goTo(invitationCodeCheck)
  }

  "Redeem Invitation Code" should {

    "show an error when NO code is entered" in new InvitationCodeRedemptionPage {
      browser.click("#updateContinue")
      browser.url must endWith("/invitationCodeError")
    }

    "show an error when an INVALID code is entered" in new InvitationCodeRedemptionPage {
      browser.fill("#invitationCode").`with`("NV4L1DC0D3")
      browser.submit("#updateContinue")
      browser.url must endWith("/invitationCodeError")
    }

    "go to start of form when a VALID code is entered" in new InvitationCodeRedemptionPage {
      browser.fill("#invitationCode").`with`("SMOKETOKEN")
      browser.submit("#updateContinue")
      browser.url must endWith("/register-to-apply/registration")
    }
  }
}


