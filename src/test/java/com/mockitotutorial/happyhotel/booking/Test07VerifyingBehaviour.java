package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test07VerifyingBehaviour {
    private BookingService bookingService;

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
    void should_InvokePayment_When_Prepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("2", LocalDate.of(2022, 07, 02),
                LocalDate.of(2022, 07, 06), 2, true);

        // when
        bookingService.makeBooking(bookingRequest);
        // then
        verify(paymentServiceMock, times(1)).pay(bookingRequest, 400);
        verifyNoMoreInteractions(paymentServiceMock);
    }

    @Test
    void should_NotInvokePayment_When_NotPrepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("2", LocalDate.of(2022, 07, 02),
                LocalDate.of(2022, 07, 06), 2, false);

        // when
        bookingService.makeBooking(bookingRequest);
        // then
        verifyNoInteractions(paymentServiceMock) ;
        verifyNoMoreInteractions(paymentServiceMock);
    }
}
