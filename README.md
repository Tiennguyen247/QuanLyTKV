# QuanLyTKV - Ứng Dụng Quản Lý Thiết Kế Viên

Ứng dụng Desktop viết bằng **Java (Java Swing)** giúp quản lý danh sách Thiết kế viên với giao diện trực quan và lưu trữ dữ liệu tự động qua tệp văn bản UTF-8.

## Kiến trúc dự án (3 tầng)
- `src/model/ThietKeVien.java`: Lớp thực thể định nghĩa thông tin Thiết kế viên và logic tính thu nhập thực lĩnh (`LuongCoBan + SoNamKN * 500,000`).
- `src/service/QuanLyThietKeVien.java`: Tầng xử lý nghiệp vụ (CRUD, tìm kiếm theo tên, sắp xếp lương giảm dần, đọc/ghi tệp `thietkevien.txt`).
- `src/ui/MainFrame.java`: Giao diện đồ họa Java Swing (`JFrame`, `JTable`, form nhập liệu và xử lý sự kiện).
- `src/ui/Main.java`: Phiên bản giao diện dòng lệnh (Console) dự phòng.

## Tính năng chính
- **Quản lý danh sách (CRUD):** Thêm mới, cập nhật thông tin, xóa thiết kế viên có kiểm tra ràng buộc dữ liệu đầu vào.
- **Tìm kiếm & Sắp xếp:** Tìm kiếm nhanh theo từ khóa tên (không phân biệt hoa/thường) và sắp xếp danh sách theo lương cơ bản giảm dần.
- **Lưu trữ tự động (File I/O):** Tự động nạp dữ liệu từ `thietkevien.txt` khi mở ứng dụng và lưu lại toàn bộ thay đổi khi đóng cửa sổ.

## Hướng dẫn biên dịch & chạy ứng dụng
1. **Biên dịch mã nguồn:**
   ```bash
   javac -d bin src/model/*.java src/service/*.java src/ui/*.java