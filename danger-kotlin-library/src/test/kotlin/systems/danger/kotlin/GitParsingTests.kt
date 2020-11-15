package systems.danger.kotlin

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test

class GitParsingTests {

    private val jsonFiles = JSONFiles()
    private val dsl: DSL
        get() = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }.decodeFromString(jsonFiles.githubDangerJSON)

    @Test
    fun testItParsesCorrectlyTheGitFiles() {
        val git = dsl.danger.git

        assertTrue(expectedModifiedFiles.containsAll(git.modifiedFiles.toList()))
        assertTrue(git.createdFiles[0] == ".ruby-version")
    }

    private val expectedModifiedFiles = listOf(
        ".travis.yml",
        "Kiosk.xcodeproj/project.pbxproj",
        "Kiosk/App/Logger.swift",
        "Kiosk/App/Networking/NetworkLogger.swift",
        "Kiosk/App/Networking/Networking.swift",
        "Kiosk/App/Networking/XAppToken.swift",
        "Kiosk/Auction Listings/ListingsViewModel.swift",
        "Kiosk/HelperFunctions.swift",
        "Kiosk/Images.xcassets/AppIcon.appiconset/Contents.json",
        "KioskTests/Bid Fulfillment/ConfirmYourBidArtsyLoginViewControllerTests.swift",
        "KioskTests/Bid Fulfillment/ConfirmYourBidEnterYourEmailViewControllerTests.swift",
        "KioskTests/Bid Fulfillment/LoadingViewControllerTests.swift",
        "KioskTests/Bid Fulfillment/RegistrationEmailViewControllerTests.swift",
        "KioskTests/Bid Fulfillment/RegistrationPasswordViewModelTests.swift",
        "KioskTests/Bid Fulfillment/SwipeCreditCardViewControllerTests.swift",
        "KioskTests/ListingsViewControllerTests.swift",
        "KioskTests/Models/SaleArtworkTests.swift",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_artworks_not_for_sale__a_listings_controller__alphabetical@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_artworks_not_for_sale__a_listings_controller__grid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_artworks_not_for_sale__a_listings_controller__highest_bid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_artworks_not_for_sale__a_listings_controller__least_bids@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_artworks_not_for_sale__a_listings_controller__lowest_bid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_artworks_not_for_sale__a_listings_controller__most_bids@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_lot_numbers__a_listings_controller__alphabetical@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_lot_numbers__a_listings_controller__grid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_lot_numbers__a_listings_controller__highest_bid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_lot_numbers__a_listings_controller__least_bids@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_lot_numbers__a_listings_controller__lowest_bid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__with_lot_numbers__a_listings_controller__most_bids@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__without_lot_numbers__a_listings_controller__alphabetical@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__without_lot_numbers__a_listings_controller__grid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__without_lot_numbers__a_listings_controller__highest_bid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__without_lot_numbers__a_listings_controller__least_bids@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__without_lot_numbers__a_listings_controller__lowest_bid@2x.png",
        "KioskTests/ReferenceImages/ListingsViewControllerTests/when_displaying_stubbed_contents__without_lot_numbers__a_listings_controller__most_bids@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/default__placing_a_bid@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/ending__placing_bid_error_due_to_outbid@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/ending__placing_bid_succeeded_but_not_resolved@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/ending__placing_bid_success_highest@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/ending__placing_bid_success_not_highest@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/ending__registering_user_not_resolved@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/ending__registering_user_success@2x.png",
        "KioskTests/ReferenceImages/LoadingViewControllerTests/errors__correctly_placing_a_bid@2x.png",
        "KioskTests/ReferenceImages/RegistrationEmailViewControllerTests/looks_right_by_default@2x.png",
        "KioskTests/ReferenceImages/RegistrationEmailViewControllerTests/looks_right_with_existing_email@2x.png",
        "KioskTests/ReferenceImages/RegistrationMobileViewControllerTests/looks_right_by_default@2x.png",
        "KioskTests/ReferenceImages/RegistrationMobileViewControllerTests/looks_right_with_existing_mobile@2x.png",
        "KioskTests/ReferenceImages/RegistrationPasswordViewControllerTests/looks_right_with_a_valid_password@2x.png",
        "KioskTests/ReferenceImages/RegistrationPasswordViewControllerTests/looks_right_with_an_invalid_password@2x.png",
        "KioskTests/ReferenceImages/RegistrationPostalZipViewControllerTests/looks_right_by_default@2x.png",
        "KioskTests/ReferenceImages/RegistrationPostalZipViewControllerTests/looks_right_with_existing_postal_code@2x.png",
        "KioskTests/ReferenceImages/YourBiddingDetailsViewControllerTests/displays_bidder_number_and_PIN@2x.png",
        "KioskTests/XAppTokenSpec.swift",
        "Podfile",
        "Podfile.lock"
    )
}
