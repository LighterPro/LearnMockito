package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test03ReturningCustomValues {
    private BookingService bookingService;

    // mocks
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingDAOMock = mock(BookingDAO.class);
        mailSenderMock = mock(MailSender.class);

        bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_CountAvailablePlaces_When_OneRoomAvailable() {
        // given
        when(roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room 1", 6)));
        int expected = 6;
        // when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
        System.out.println("LOL");
    }

    @Test
    void should_CountAvailablePlaces_When_MultipleRoomsAvailable() {
        // given
        List<Room> roomList = List.of(
                new Room("Room 1", 6),
                new Room("Room 2", 1)
        );
        when(roomServiceMock.getAvailableRooms()).thenReturn(roomList);
        int expected = 7;
        // when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
    }
}
