package invitationcode

import fixtures.InvitationCodePage
import org.specs2.mutable._
import org.specs2.specification.Scope

class InvitationCodeSpec extends Specification with Scope {

  "Redeem Invitation Code" should {

    "show an error when NO code is entered" in new InvitationCodePage {
      goToInvitationCodePage
      submitInvitationCode
      currentPage must beTheErrorPage
    }

    "show an error when an INVALID code is entered" in new InvitationCodePage {
      goToInvitationCodePage
      typeInvitationCode("NV4L1DC0D3")
      submitInvitationCode
      currentPage must beTheErrorPage
    }

    "go to start of form when a VALID code is entered" in new InvitationCodePage {
      goToInvitationCodePage
      typeInvitationCode("SMOKETOKEN")
      submitInvitationCode
      currentPage must beFormStartPage
    }
  }
}


