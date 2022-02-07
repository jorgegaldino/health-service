package uk.co.mycompany.healthservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import uk.co.mycompany.healthservice.domain.dto.DoctorDto;
import uk.co.mycompany.healthservice.exception.HealthServiceException;
import uk.co.mycompany.healthservice.service.HealthService;
import uk.co.mycompany.healthservice.strategy.HealthServiceStrategy;
import uk.co.mycompany.healthservice.handle.HealthServiceHandle;
import uk.co.mycompany.healthservice.service.impl.LocalHealthService;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LocalHealthServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private HealthServiceStrategy mockHealthServiceStrategy;

    @Mock
    private HealthServiceHandle mockHealthServiceHandler;

    private HealthService mockSubscriptionService;

    private LocalHealthService localHealthService;

    @Before
    public void setUp() {
        initMocks(this);

        localHealthService = new LocalHealthService();
        localHealthService.addStrategy(mockHealthServiceStrategy);
    }

    @After
    public void tearDown() {

    }
/*
    @Test
    public void canExpireDigitalKeys() {
        SubscriptionOld subscriptionDto = SubscriptionOld.builder().build();

        when(mockAccesskeyStrategy.findExpireDigitalKeysHandler(any())).thenReturn(Optional.of(mockAccesskeyHandler));
        doNothing().when(mockAccesskeyHandler).expireDigitalKeys(subscriptionDto);
        accessKeyLocalService.expireDigitalKeys(subscriptionDto);
    }

    @Test
    public void canHandleEvent() {
        SubscriptionOld subscription = SubscriptionOld.builder().build();
        SubscriptionUpdateEvent event = new SubscriptionUpdateEvent(mockSubscriptionService, subscription);

        when(mockAccesskeyStrategy.findExpireDigitalKeysHandler(any())).thenReturn(Optional.of(mockAccesskeyHandler));
        doNothing().when(mockAccesskeyHandler).expireDigitalKeys(subscription);
        accessKeyLocalService.onEvent(event);
    }

    @Test
    public void doesntRespondToUnrecognisedEvent() {
        Event<String, String> subscription = new Event<String, String>() {
            @Override
            public String getSource() {
                return null;
            }

            @Override
            public String getData() {
                return null;
            }
        };

        accessKeyLocalService.onEvent(subscription);
    }

    @Test
    public void canHandleEventException() {
        SubscriptionOld subscription = SubscriptionOld.builder().build();
        SubscriptionUpdateEvent event = new SubscriptionUpdateEvent(mockSubscriptionService, subscription);

        when(mockAccesskeyStrategy.findExpireDigitalKeysHandler(any())).thenReturn(Optional.of(mockAccesskeyHandler));
        doThrow(new AccessKeyServiceException("Test")).when(mockAccesskeyHandler).expireDigitalKeys(subscription);
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Test");

        accessKeyLocalService.onEvent(event);
    }

    @Test
    public void willThrowExceptionWhenWhiteListAccessKey() {

        AccessKeyWhiteListDto accessKeyWhiteListDto = AccessKeyWhiteListDto.builder().build();

        when(mockAccesskeyStrategy.findWhiteListingAccessKeyPanHandler(accessKeyWhiteListDto))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.whiteListingAccessKeyPan(accessKeyWhiteListDto))
            .thenThrow(new AccessKeyServiceException("Test"));

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Test");

        boolean response = accessKeyLocalService.whiteListingAccessKeyPan(accessKeyWhiteListDto);

        verify(mockEventHandler, times(0)).fireEvent(any());
    }

    @Test
    public void willThrowExceptionWhenHandlerNotFoundForWhiteListAccessKey() {

        AccessKeyWhiteListDto accessKeyWhiteListDto = AccessKeyWhiteListDto.builder().build();

        when(mockAccesskeyStrategy.findWhiteListingAccessKeyPanHandler(accessKeyWhiteListDto))
            .thenReturn(Optional.empty());

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler not found request.");

        boolean response = accessKeyLocalService.whiteListingAccessKeyPan(accessKeyWhiteListDto);

        verify(mockEventHandler, times(0)).fireEvent(any());
    }
*/
    @Test
    public void testGetAllTitles() {

        Map<String, Object> extendedData = new HashMap<>();
        List<String> listOfTitles = new ArrayList<>();
        listOfTitles.add("Paediatrician");

        when(mockHealthServiceStrategy.findSearchDoctorsTitlesHandler())
            .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listAllDoctorsTitles())
            .thenReturn(listOfTitles);

        List<String> result = localHealthService.listAllDoctorsTitles();

        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testListDoctorsByTitleAndAddress() {

        List<DoctorDto> listOfDoctors = new ArrayList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAddress("Cardinal Hume Centre, 3-7 Arneway St, London SW1P 2BG");
        doctorDto.setName("Karen Smith");
        doctorDto.setTitle("Paediatrician");
        listOfDoctors.add(doctorDto);

        when(mockHealthServiceStrategy.findSearchDoctorsHandler(anyString(),anyString()))
                .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listByTitleAndLocation(anyString(), anyString()))
                .thenReturn(listOfDoctors);

        List<DoctorDto> result = localHealthService.listDoctorsByTitleAndLocation("Paediatrician", "London");

        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testListDoctorsByTitleAndLocation() {

        List<DoctorDto> listOfDoctors = new ArrayList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAddress("Cardinal Hume Centre, 3-7 Arneway St, London SW1P 2BG");
        doctorDto.setName("Karen Smith");
        doctorDto.setTitle("Paediatrician");
        listOfDoctors.add(doctorDto);

        when(mockHealthServiceStrategy.findSearchDoctorsHandler(anyString(),anyDouble(), anyDouble()))
                .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listByTitleAndLocation(anyString(), anyDouble(), anyDouble()))
                .thenReturn(listOfDoctors);

        List<DoctorDto> result = localHealthService.listDoctorsByTitleAndLocation("Paediatrician", -54.000121, 0.2121121);

        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testListAllDoctors() {

        List<DoctorDto> listOfDoctors = new ArrayList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAddress("Cardinal Hume Centre, 3-7 Arneway St, London SW1P 2BG");
        doctorDto.setName("Karen Smith");
        doctorDto.setTitle("Paediatrician");
        listOfDoctors.add(doctorDto);

        when(mockHealthServiceStrategy.findSearchDoctorsHandler())
                .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listAll())
                .thenReturn(listOfDoctors);

        List<DoctorDto> result = localHealthService.listAllDoctors();

        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testListDoctorsByTitle() {

        List<DoctorDto> listOfDoctors = new ArrayList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAddress("Cardinal Hume Centre, 3-7 Arneway St, London SW1P 2BG");
        doctorDto.setName("Karen Smith");
        doctorDto.setTitle("Paediatrician");
        listOfDoctors.add(doctorDto);

        when(mockHealthServiceStrategy.findSearchDoctorsHandler(anyString()))
                .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listByTitle("Paediatrician"))
                .thenReturn(listOfDoctors);

        List<DoctorDto> result = localHealthService.listDoctorsByTitle("Paediatrician");

        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testDistanceListDoctorsByTitleAndLocation() {

        List<DoctorDto> listOfDoctors = new ArrayList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAddress("Cardinal Hume Centre, 3-7 Arneway St, London SW1P 2BG");
        doctorDto.setName("Karen Smith");
        doctorDto.setTitle("Paediatrician");
        doctorDto.setDistance(13.8);
        listOfDoctors.add(doctorDto);

        when(mockHealthServiceStrategy.findSearchDoctorsHandler(anyString(),anyDouble(), anyDouble()))
                .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listByTitleAndLocation(anyString(), anyDouble(), anyDouble()))
                .thenReturn(listOfDoctors);

        List<DoctorDto> result = localHealthService.listDoctorsByTitleAndLocation("Paediatrician", -54.000121, 0.2121121);

        assertThat(result.size()).isGreaterThan(0);
        assertEquals("13.8000 Miles",result.get(0).getFormattedDistance());
    }

    @Test(expected = HealthServiceException.class)
    public void testListDoctorsByTitleAndLocationThrowsHealthServiceException() {

        List<DoctorDto> listOfDoctors = new ArrayList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAddress("Cardinal Hume Centre, 3-7 Arneway St, London SW1P 2BG");
        doctorDto.setName("Karen Smith");
        doctorDto.setTitle("Paediatrician");
        listOfDoctors.add(doctorDto);

        HealthServiceException healthServiceException = new HealthServiceException(String.format("Error Location API - %s", "Bad format"));

        when(mockHealthServiceStrategy.findSearchDoctorsHandler(anyString(),anyString()))
                .thenReturn(Optional.of(mockHealthServiceHandler));

        when(mockHealthServiceHandler.listByTitleAndLocation(anyString(), anyString()))
                .thenThrow(healthServiceException);

        List<DoctorDto> result = localHealthService.listDoctorsByTitleAndLocation("Paediatrician", "London");

    }


    @Test
    public void listDoctorsByTitleAndLocation_WhenStrategyNotFound_ShouldThrowAnError() {
        exceptionRule.expect(HealthServiceException.class);
        exceptionRule.expectMessage("Handler not found request.");

        when(mockHealthServiceStrategy.findSearchDoctorsHandler(anyString(), anyString()))
                .thenReturn(Optional.empty());

        localHealthService.listDoctorsByTitleAndLocation("Paediatrician", "London");
    }




/*
    @Test
    public void willThrowExceptionWhenHandlerNotFoundForGetAccessKeys() {

        Map<String, Object> extendedData = new HashMap<>();

        when(mockAccesskeyStrategy.findGetAccessKeysHandler(anyMap()))
            .thenReturn(Optional.empty());

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for getAccessKeys method not found");

        accessKeyLocalService.getAccessKeys(extendedData);
    }

    @Test
    public void willThrowExceptionWhenGetAccessKeys() {

        Map<String, Object> extendedData = new HashMap<>();
        List<AccessKeyPanCardDto> listOfAccessKeys = new ArrayList();
        listOfAccessKeys.add(AccessKeyPanCardDto.builder().build());

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Test");

        when(mockAccesskeyStrategy.findGetAccessKeysHandler(anyMap()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        when(mockAccesskeyHandler.getAccessKeys(anyMap()))
            .thenThrow(new AccessKeyServiceException("Test"));

        accessKeyLocalService.getAccessKeys(extendedData);
    }

    @Test
    public void addAccessKeyToSubscription_WhenGivenAbstractAccessKey_ShouldUseStrategyHandler() {
        AccessKey accessKeyCardDto = AccessKeyCardDto.builder().build();

        when(mockAccesskeyStrategy.findAddAccessKeyToSubscriptionHandler(any()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        Mockito.doNothing().when(mockAccesskeyHandler).addAccessKeyToSubscription(any(AccessKey.class));

        accessKeyLocalService.addAccessKeyToSubscription(accessKeyCardDto);
    }

    @Test
    public void addAccessKeyToSubscription_WhenStrategyNotFound_ShouldThrowAnError() {
        AccessKey accessKeyCardDto = AccessKeyCardDto.builder().build();

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler not found request.");

        when(mockAccesskeyStrategy.findAddAccessKeyToSubscriptionHandler(any()))
            .thenReturn(Optional.empty());

        accessKeyLocalService.addAccessKeyToSubscription(accessKeyCardDto);
    }

    @Test
    public void addAccessKeyToSubscription_WhenHandlerThrowsAnException_ShouldThrowItBack() {
        AccessKey accessKeyCardDto = AccessKeyCardDto.builder().build();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("TEST");

        when(mockAccesskeyStrategy.findAddAccessKeyToSubscriptionHandler(any()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        Mockito.doThrow(new Exception("TEST")).when(mockAccesskeyHandler)
            .addAccessKeyToSubscription(any(AccessKey.class));

        accessKeyLocalService.addAccessKeyToSubscription(accessKeyCardDto);
    }

    @Test(expected = NullPointerException.class)
    public void addAccessKeyToSubscription_WhenGivenNullAccessKey_ShouldNotAcceptItThrowingNullPointerException() {
        accessKeyLocalService.addAccessKeyToSubscription(null);
    }

    @Test
    public void whitelistAccessKey_WhenGivenAbstractWhitelistAccessKey_ShouldUseStrategyHandler() {
        AccessKeyWhitelistDomainDto mockAccessKey = MembershipCardAccessKeyWhitelistDomainDto.builder().build();

        when(mockAccesskeyStrategy.findWhitelistAccessKeyHandler(any()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        when(mockAccesskeyHandler.whitelistAccessKey(any(AccessKeyWhitelistDomainDto.class))).thenReturn(mockAccessKey);

        AccessKeyWhitelistDomainDto response = accessKeyLocalService.whitelistAccessKey(mockAccessKey);

        assertThat(response).isEqualTo(mockAccessKey);
    }

    @Test
    public void whitelistAccessKey_WhenStrategyNotFound_ShouldThrowAnError() {
        AccessKeyWhitelistDomainDto accessKey = MembershipCardAccessKeyWhitelistDomainDto.builder().build();

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler not found for whitelistAccessKey operation.");

        when(mockAccesskeyStrategy.findWhitelistAccessKeyHandler(any()))
            .thenReturn(Optional.empty());

        accessKeyLocalService.whitelistAccessKey(accessKey);
    }

    @Test
    public void whitelistAccessKey_WhenHandlerThrowsAnException_ShouldThrowItBack() {
        AccessKeyWhitelistDomainDto accessKey = MembershipCardAccessKeyWhitelistDomainDto.builder().build();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("TEST");

        when(mockAccesskeyStrategy.findWhitelistAccessKeyHandler(any()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        Mockito.doThrow(new Exception("TEST")).when(mockAccesskeyHandler)
            .whitelistAccessKey(any(AccessKeyWhitelistDomainDto.class));

        accessKeyLocalService.whitelistAccessKey(accessKey);
    }

    @Test(expected = NullPointerException.class)
    public void whitelistAccessKey_WhenGivenNullAccessKey_ShouldNotAcceptItThrowingNullPointerException() {
        accessKeyLocalService.whitelistAccessKey(null);
    }

    @Test
    public void getWhitelistedAccessKeys_WhenGivenAbstractWhitelistAccessKey_ShouldUseStrategyHandler() {
        Long productId = 123L;
        Map<String, String> serializedExtendedData = new HashMap<>();
        AccessKeyWhitelistDomainDto resultMockAccessKey = MembershipCardAccessKeyWhitelistDomainDto.builder().build();
        List<AccessKeyWhitelistDomainDto> resultMock = Collections.singletonList(resultMockAccessKey);

        when(mockAccesskeyStrategy.findGetWhitelistedAccessKeysHandler(any()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        when(mockAccesskeyHandler.getWhitelistedAccessKeys(new GetWhitelistedAccessKeysDto(productId, serializedExtendedData)))
            .thenReturn(resultMock);

        List<AccessKeyWhitelistDomainDto> response =
            accessKeyLocalService.getWhitelistedAccessKeys(
                new GetWhitelistedAccessKeysDto(productId, serializedExtendedData));

        assertThat(response).isEqualTo(resultMock);
    }

    @Test
    public void getWhitelistedAccessKeys_WhenStrategyNotFound_ShouldThrowAnError() {
        Long productId = 123L;
        Map<String, String> serializedExtendedData = new HashMap<>();

        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler not found for getWhitelistedAccessKeys operation.");

        when(mockAccesskeyStrategy.findGetWhitelistedAccessKeysHandler(any()))
            .thenReturn(Optional.empty());

        accessKeyLocalService.getWhitelistedAccessKeys(
            new GetWhitelistedAccessKeysDto(productId, serializedExtendedData));
    }

    @Test
    public void getWhitelistedAccessKeys_WhenHandlerThrowsAnException_ShouldThrowItBack() {
        Long productId = 123L;
        Map<String, String> serializedExtendedData = new HashMap<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("TEST");

        when(mockAccesskeyStrategy.findGetWhitelistedAccessKeysHandler(any()))
            .thenReturn(Optional.of(mockAccesskeyHandler));

        Mockito.doThrow(new Exception("TEST")).when(mockAccesskeyHandler)
            .whitelistAccessKey(any(AccessKeyWhitelistDomainDto.class));

        accessKeyLocalService.getWhitelistedAccessKeys(
            new GetWhitelistedAccessKeysDto(productId, serializedExtendedData));
    }

    @Test
    public void getAssociatePrefixAndClientId_success(){
        String expected = "700024";
        when(mockAccesskeyStrategy.findGetClientIdHandler("70002446912345"))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.getClientId("70002446912345")).thenReturn(expected);
        String response = accessKeyLocalService.getClientId("70002446912345");
        assertEquals(response,"700024");
    }

    @Test
    public void getAssociatePrefixAndClientId_WhenHandlerNotFound_ShouldThrowException(){
        when(mockAccesskeyStrategy.findGetClientIdHandler("70002446912345"))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler not found for the getClientId request");
        accessKeyLocalService.getClientId("70002446912345");
    }

    @Test
    public void isCardWhitelisted_returnsTrue(){
        when(mockAccesskeyStrategy.findIsCardWhiteListedHandler("12345"))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.isCardWhiteListed("12345")).thenReturn( true);
        boolean response = accessKeyLocalService.isCardWhiteListed("12345");
        assertTrue(response);
    }

    @Test
    public void isCardWhitelisted_WhenHandlerNotFound_ShouldThrowException(){
        when(mockAccesskeyStrategy.findIsCardWhiteListedHandler("123457"))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for isCardWhiteListed method not found");
        accessKeyLocalService.isCardWhiteListed("123457");
    }

    @Test
    public void isCardNameMatchingWhiteListedCardName_returnsTrue(){
        when(mockAccesskeyStrategy.findIsCardNameMatchingWhiteListedCardNameHandler("John Smith"))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.isCardNameMatchingWhiteListedCardName("John Smith")).thenReturn(true);
        boolean response = accessKeyLocalService.isCardNameMatchingWhiteListedCardName("John Smith");
        assertTrue(response);
    }

    @Test
    public void isCardNameMatchingWhiteListedCardName_WhenHandlerNotFound_ShouldThrowException(){
        when(mockAccesskeyStrategy.findIsCardNameMatchingWhiteListedCardNameHandler("John Smith"))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for isCardNameMatchingWhiteListedCardName method not found");
        accessKeyLocalService.isCardNameMatchingWhiteListedCardName("John Smith");
    }

    @Test
    public void getDmc_WhenHandlerFound_ShouldReturnBarcodeSuccessfully() {
        when(mockAccesskeyStrategy.findGetDmcHandler("2242424"))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.getDmc(anyString())).thenReturn("Barcode");
        String response = accessKeyLocalService.getDmc("2242424");
        assertEquals("Barcode", response);
    }

    @Test
    public void getDmc_WhenHandlerNotFound_ShouldThrowException() {
        when(mockAccesskeyStrategy.findGetDmcHandler("2242424"))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for getDmc method not found");
        accessKeyLocalService.getDmc("2242424");
    }

    @Test
    public void validateAccessKeyForRedemption_WhenHandlerFound_ShouldReturnValidateAccessKeyForRedemptionResponseSuccessfully() {
        ValidateAccessKeyForRedemptionResponseDto mockResponse = ValidateAccessKeyForRedemptionResponseDto.builder().build();
        when(mockAccesskeyStrategy.findValidateAccessKeyForRedemptionHandler("123456"))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.validateAccessKeyForRedemption(anyString())).thenReturn(mockResponse);
        ValidateAccessKeyForRedemptionResponseDto response = accessKeyLocalService.validateAccessKeyForRedemption("123456");
        assertEquals(mockResponse, response);
    }

    @Test
    public void validateAccessKeyForRedemption_WhenHandlerNotFound_ShouldThrowException() {
        when(mockAccesskeyStrategy.findValidateAccessKeyForRedemptionHandler("123456"))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for validateAccessKeyForRedemption method not found");
        accessKeyLocalService.validateAccessKeyForRedemption("123456");
    }

    @Test
    public void findAccessKey_WhenHandlerFound_ShouldReturnAccessKeySuccessfully() {
        AccessKeyRequestDto accessKeyRequestDto = AccessKeyRequestDto.builder().build();
        AccessKeyResponseDto mockAccesskey = AccessKeyResponseDto.builder().build();
        when(mockAccesskeyStrategy.findFindAccessKeyHandler(accessKeyRequestDto))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.findAccessKey(any())).thenReturn(mockAccesskey);
        AccessKeyResponseDto response = accessKeyLocalService.findAccessKey(accessKeyRequestDto);
        assertEquals(mockAccesskey, response);
    }

    @Test
    public void findAccessKey_WhenHandlerNotFound_ShouldThrowException() {
        AccessKeyRequestDto accessKeyRequestDto = AccessKeyRequestDto.builder().build();
        when(mockAccesskeyStrategy.findFindAccessKeyHandler(accessKeyRequestDto))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for findAccessKey method not found");
        accessKeyLocalService.findAccessKey(accessKeyRequestDto);
    }

    @Test
    public void createAccessKey_WhenHandlerFound_ShouldReturnAccessKeyResponseSuccessfully() {
        AccessKeyCreateRequest accessKeyCreateRequest = AccessKeyCreateRequest.builder().build();
        AccessKeyResponseDto mockAccessKeyResponse = AccessKeyResponseDto.builder().build();

        when(mockAccesskeyStrategy.findCreateAccessKeyHandler(accessKeyCreateRequest))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.createAccessKey(any())).thenReturn(mockAccessKeyResponse);

        AccessKeyResponseDto response = accessKeyLocalService.createAccessKey(accessKeyCreateRequest);
        assertEquals(mockAccessKeyResponse, response);
    }

    @Test
    public void createAccessKey_WhenHandlerNotFound_ShouldThrowException() {
        AccessKeyCreateRequest accessKeyCreateRequest = AccessKeyCreateRequest.builder().build();

        when(mockAccesskeyStrategy.findCreateAccessKeyHandler(accessKeyCreateRequest))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for createAccessKey method not found");
        accessKeyLocalService.createAccessKey(accessKeyCreateRequest);
    }

    @Test
    public void searchAccessKey_WhenHandlerNotFound_ShouldThrowException() {
        DigitalMembershipCardAccessKey digitalMembershipCardAccessKey = DigitalMembershipCardAccessKey.builder().build();


        when(mockAccesskeyStrategy.findSearchAccessKeyHandler(digitalMembershipCardAccessKey))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for searchAccessKey method not found");
        accessKeyLocalService.searchAccessKey(digitalMembershipCardAccessKey);
    }

    @Test
    public void searchAccessKey_WhenHandlerFound_ShouldReturnAccessKeyResponseSuccessfully() {
        DigitalMembershipCardAccessKey digitalMembershipCardAccessKey = DigitalMembershipCardAccessKey.builder().build();
        AccessKeyResponseDto mockAccessKeyResponse = AccessKeyResponseDto.builder().build();
        when(mockAccesskeyStrategy.findSearchAccessKeyHandler(digitalMembershipCardAccessKey))
            .thenReturn(Optional.of(mockSearchAccesskeyHandler));
        when(mockSearchAccesskeyHandler.searchAccessKey(digitalMembershipCardAccessKey)).thenReturn(mockAccessKeyResponse);
        AccessKeyResponseDto response = accessKeyLocalService.searchAccessKey(digitalMembershipCardAccessKey);
        assertEquals(mockAccessKeyResponse, response);
    }

    @Test
    public void searchAccessKeys_WhenHandlerFound_ShouldReturnAccessKeySuccessfully() {
        AccessKeySearchRequest accessKeySearchRequest = AccessKeySearchRequest.builder().build();
        AccessKeyResponseDto resultMockAccessKey = AccessKeyResponseDto.builder().build();
        List<AccessKeyResponseDto> resultMock = Collections.singletonList(resultMockAccessKey);

        when(mockAccesskeyStrategy.findSearchAccessKeysHandler(accessKeySearchRequest))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.searchAccessKeys(any())).thenReturn(resultMock);
        List<AccessKeyResponseDto> response = accessKeyLocalService.searchAccessKeys(accessKeySearchRequest);
        assertThat(response).isEqualTo(resultMock);
    }

    @Test
    public void searchAccessKeys_WhenHandlerNotFound_ShouldThrowException() {
        AccessKeySearchRequest accessKeySearchRequest = AccessKeySearchRequest.builder().build();
        when(mockAccesskeyStrategy.findSearchAccessKeysHandler(accessKeySearchRequest))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler for searchAccessKeys method not found");
        accessKeyLocalService.searchAccessKeys(accessKeySearchRequest);
    }

    @Test
    public void createOrUpdateAccessKey_WhenHandlerFound_ShouldReturnAccessKeyResponseSuccessfully() {
        AccessKeyCreateOrUpdateRequest accessKeyCreateOrUpdateRequest = AccessKeyCreateOrUpdateRequest.builder().build();
        AccessKeyResponseDto mockAccessKeyResponse = AccessKeyResponseDto.builder().build();

        when(mockAccesskeyStrategy.findCreateOrUpdateAccessKeyHandler(accessKeyCreateOrUpdateRequest))
            .thenReturn(Optional.of(mockAccesskeyHandler));
        when(mockAccesskeyHandler.createOrUpdateAccessKey(any())).thenReturn(mockAccessKeyResponse);

        AccessKeyResponseDto response = accessKeyLocalService.createOrUpdateAccessKey(accessKeyCreateOrUpdateRequest);
        assertEquals(mockAccessKeyResponse, response);
    }

    @Test
    public void createOrUpdateAccessKey_WhenHandlerNotFound_ShouldThrowException() {
        AccessKeyCreateOrUpdateRequest accessKeyCreateOrUpdateRequest = AccessKeyCreateOrUpdateRequest.builder().build();

        when(mockAccesskeyStrategy.findCreateOrUpdateAccessKeyHandler(accessKeyCreateOrUpdateRequest))
            .thenReturn(Optional.empty());
        exceptionRule.expect(AccessKeyServiceException.class);
        exceptionRule.expectMessage("Handler not found request.");
        accessKeyLocalService.createOrUpdateAccessKey(accessKeyCreateOrUpdateRequest);
    }
*/
}
