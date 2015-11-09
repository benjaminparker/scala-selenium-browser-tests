import org.fluentlenium.core.Fluent
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.specs2.matcher.{Matcher, MatchResult}
import org.specs2.mutable._
import org.specs2.specification.Scope

class InvitationCodeSpec extends Specification with Scope {

  trait InvitationCodeRedemptionPage extends Scope {
    val page = new Fluent(new HtmlUnitDriver()) {}
    page.getCookies.clear
    page.goTo("http://localhost:3022/register-to-apply/invitationCodeCheck")

    def invitationCode: String => Fluent = code => page.fill("#invitationCode").`with`(code)
    def submit = page.click("#updateContinue")
    def beErrorPageURL: String => MatchResult[String] = url => url must endWith("/invitationCodeError")
    def beFormStartPageURL: String => MatchResult[String] = url => url must endWith("/register-to-apply/registration")
  }

  "Redeem Invitation Code" should {

    "show an error when NO code is entered" in new InvitationCodeRedemptionPage {
      submit
      page.url must beErrorPageURL
    }

    "show an error when an INVALID code is entered" in new InvitationCodeRedemptionPage {
      invitationCode("NV4L1DC0D3")
      submit
      page.url must beErrorPageURL
    }

    "go to start of form when a VALID code is entered" in new InvitationCodeRedemptionPage {
      invitationCode("SMOKETOKEN")
      submit
      page.url must beFormStartPageURL
    }
  }
}


