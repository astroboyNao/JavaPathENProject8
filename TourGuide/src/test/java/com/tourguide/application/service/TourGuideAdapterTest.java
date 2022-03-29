package com.tourguide.application.service;

public class TourGuideAdapterTest {
/*
    @Mock
    public TrackerAdapter tracker;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepositoryImpl userRepository;
    @Mock
    private TourGuide tourGuide;

    @InjectMocks
    TourGuideAdapter tourGuideAdapter;


    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserLocation() {
        User user = User.builder().userName("userName").build();
        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().build();
        VisitedLocation visitedLocation = VisitedLocation.builder().user(userDomain).build();

        Mockito.when(userRepository.get(user.getUserName())).thenReturn(Mono.just(user));
        Mockito.when(userMapper.toUserDomain(user)).thenReturn(userDomain);
        Mockito.when(tourGuide.getUserLocation(userDomain)).thenReturn(Mono.just(visitedLocation));


        StepVerifier.create(tourGuideAdapter.getUserLocation("userName"))
                .expectNext(visitedLocation)
                .expectComplete()
                .verify();
    }

    @Test
    public void getNearByAttractions() {
        User user = User.builder().userName("userName").build();
        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().build();
        Attraction attraction = Attraction.builder().attractionName("attractionName").build();

        Mockito.when(userRepository.get(user.getUserName())).thenReturn(Mono.just(user));
        Mockito.when(userMapper.toUserDomain(user)).thenReturn(userDomain);
        Mockito.when(tourGuide.getNearAttractionsByUser(userDomain)).thenReturn(Flux.just(attraction));

        StepVerifier.create(tourGuideAdapter.getNearByAttractions("userName"))
                .expectNext(attraction)
                .expectComplete()
                .verify();
    }

    @Test
    public void getUserRewards() {
        User user = User.builder().userName("userName").build();
        Reward reward = Reward.builder().rewardPoints(1).build();
        List<Reward> rewards = List.of(reward);

        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().rewards(rewards).build();

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(Mono.just(user));
        Mockito.when(userMapper.toUserDomain(user)).thenReturn(userDomain);

        StepVerifier.create(tourGuideAdapter.getUserRewards("userName"))
                .expectNext(reward)
                .expectComplete()
                .verify();
    }


    @Test
    public void getTripDeals() {
        User user = User.builder().userName("userName").build();
        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().build();
        Provider provider = Provider.builder().price(0D).name("provider").build();

        Mockito.when(userRepository.get(user.getUserName())).thenReturn(Mono.just(user));
        Mockito.when(userMapper.toUserDomain(user)).thenReturn(userDomain);
        Mockito.when(tourGuide.getUserTripDeals(userDomain)).thenReturn(Flux.just(provider));

        StepVerifier.create(tourGuideAdapter.getTripDeals("userName"))
                .expectNext(provider)
                .expectComplete()
                .verify();
    }

    @Test
    public void getAllUsers() {
        User user = User.builder().userName("userName").build();
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = tourGuideAdapter.getAllUsers();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    public void trackUserLocation() {
        User user = User.builder().userName("userName").build();
        Reward reward = Reward.builder().rewardPoints(1).build();
        List<Reward> rewards = List.of(reward);
        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().rewards(rewards).build();

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(Mono.just(user));
        Mockito.when(userMapper.toUserDomain(user)).thenReturn(userDomain);
        Mockito.when(userMapper.toUserEntities(userDomain)).thenReturn(user);
        Mockito.when(tourGuide.trackUserLocation(userDomain)).thenReturn(Mono.just(userDomain));

        StepVerifier.create(tourGuideAdapter.trackUserLocation(user))
               // .expectNextCount(0)
                .verifyComplete();

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

*/
}