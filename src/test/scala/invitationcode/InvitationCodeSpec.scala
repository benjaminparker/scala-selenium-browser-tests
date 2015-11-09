package invitationcode

import fixtures.InvitationCodePage
import org.specs2.mutable._
import org.specs2.specification.Scope

class InvitationCodeSpec extends Specification with Scope {

  "Redeem Invitation Code" should {

    "show an error when NO code is entered" in new InvitationCodePage {
      submit
      page.url must beErrorPageURL
    }

    "show an error when an INVALID code is entered" in new InvitationCodePage {
      invitationCode("NV4L1DC0D3")
      submit
      page.url must beErrorPageURL
    }

    "go to start of form when a VALID code is entered" in new InvitationCodePage {
      invitationCode("SMOKETOKEN")
      submit
      page.url must beFormStartPageURL
    }
  }
}


