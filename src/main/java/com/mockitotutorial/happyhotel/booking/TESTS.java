package com.mockitotutorial.happyhotel.booking;

import java.util.List;

public class TESTS {
    public static void main(String[] args) {
        System.out.println(findAvailableRoomId());
    }

    public static String findAvailableRoomId() {
        List<Integer> list = List.of(1, 2, 3, 1, 2, 3, 4, 5, 6, 6, 7);
        return list.stream()
                .filter(i -> i >= 42)
                .findFirst()
                .map(String::valueOf)
                .orElseThrow(BusinessException::new);
    }

}
