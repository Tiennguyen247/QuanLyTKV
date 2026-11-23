# QuanLyTKV - Windows Desktop Designer Management Application

Ứng dụng Desktop viết bằng **Java SE & Java Swing**, hỗ trợ quản lý hồ sơ Thiết kế viên, tự động tính toán quỹ lương thực lĩnh, lưu trữ dữ liệu an toàn và xuất báo cáo Excel (`.csv`).

![Giao diện ứng dụng QuanLyTKV](docs/demo-ui.png)

## Tính năng nổi bật
- **Giao diện chuẩn Windows Desktop:** Tích hợp `Windows Look & Feel`, biểu tượng ứng dụng tùy chỉnh vẽ bằng `Graphics2D`, khóa chỉnh sửa trực tiếp trên bảng `JTable` để bảo toàn dữ liệu.
- **Quản lý hồ sơ (CRUD) & Ràng buộc dữ liệu:** Thêm, sửa, xóa thiết kế viên với bộ kiểm tra Regex (định dạng ngày sinh `dd/MM/yyyy`, số điện thoại 10 chữ số, chặn ký tự phân tách `;`).
- **Thống kê thời gian thực (Real-time Summary):** Tự động tính tổng nhân sự và tổng quỹ lương thực lĩnh ngay trên thanh trạng thái theo kết quả hiển thị.
- **Tìm kiếm nhanh & Sắp xếp:** Lọc theo tên không phân biệt hoa/thường (hỗ trợ phím tắt `Enter`) và sắp xếp theo lương cơ bản giảm dần.
- **Tự động lưu (Auto-Save) & Xuất Excel (.csv):** Tự động đồng bộ dữ liệu xuống tệp UTF-8 sau mỗi thao tác thay đổi; hỗ trợ xuất báo cáo `.csv` chuẩn **UTF-8 BOM** tương thích trực tiếp với Microsoft Excel / WPS Office.

## Kiến trúc dự án (3-Layer Architecture)
```text
QuanLyTKV/
├── src/
│   ├── model/ThietKeVien.java           # Thực thể dữ liệu & logic tính thu nhập thực lĩnh
│   ├── service/QuanLyThietKeVien.java   # Xử lý nghiệp vụ CRUD, tìm kiếm, sắp xếp, File I/O & CSV Export
│   └── ui/
│       ├── MainFrame.java               # Giao diện đồ họa chính (Java Swing)
│       └── Main.java                    # Giao diện dòng lệnh (Console) dự phòng
├── docs/demo-ui.png                     # Ảnh minh họa giao diện ứng dụng
├── manifest.txt                         # Cấu hình đóng gói tệp thực thi JAR
└── README.md