package com.example.unittest;

import java.util.List;

public class TinhToanService {
    public static int tinhTong(int a, int b) {
        return a + b;
    }

    public static int tinhHieu(int a, int b) {
        return a - b;
    }

    public static int tinhTich(int a, int b) {
        return a * b;
    }

    public static double tinhThuong(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Không thể chia cho 0");
        }
        return (double) a / b;
    }

    public static double tinhTrungBinhCong(int a, int b) {
        return (a + b) / 2.0;
    }






    public static int timViTriMang(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }




    public static int tinhTongNSoNguyen(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static int tinhTongSoLe(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            if (num % 2 != 0) {
                sum += num;
            }
        }
        return sum;
    }

    public static boolean tinhSoNguyenAm(int[] numbers) {
        for (int num : numbers) {
            if (num < 0) {
                return true;
            }
        }
        return false;
    }


    public static String kiemTraSo(int number) {
        if (number > 0) {
            return "Dương";
        } else if (number < 0) {
            return "Âm";
        } else {
            return "Không";
        }
    }




    public static double tinhTrungBinhCong(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new ArithmeticException("Danh sách rỗng");
        }
        return numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
    }



    public static int getElementAtIndex(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index ngoài phạm vi");
        }
        return array[index];
    }


    public static String getUserName(User user) {
        if (user == null) {
            throw new NullPointerException("Người dùng chưa được tạo");
        }
        return user.getName();
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
