# SE Advanced Report

### Danh sách nhóm

- 2312205 - Võ Tiến Nam - nam.votien0610@hcmut.edu.vn

### Public URL Web Service (Lab 5)

Ứng dụng được deploy tại:

- Base URL: `https://se-advance-252.onrender.com`
- List View (Danh sách sinh viên): `https://se-advance-252.onrender.com/students`
- Detail View (Chi tiết sinh viên): `https://se-advance-252.onrender.com/students/{id}`

### Mô tả giao diện Web

#### 1.1 Trang Danh Sách (List View)

- **Đường dẫn (URL)**: `/students`
- **Yêu cầu giao diện**:
  - Hiển thị danh sách toàn bộ sinh viên dưới dạng bảng.
  - Tích hợp ô nhập liệu để **tìm kiếm sinh viên theo tên**.
  - Nút **"Thêm Mới"** để điều hướng sang trang tạo sinh viên.
  - Mỗi dòng dữ liệu có liên kết hoặc nút **"Xem Chi Tiết"**.

#### 1.2 Trang Chi Tiết (Detail View)

- **Đường dẫn (URL)**: `/students/{id}`
- **Yêu cầu giao diện**:
  - Hiển thị đầy đủ thông tin chi tiết của sinh viên: **ID, Tên, Email, Tuổi**.
  - Nút **"Chỉnh Sửa"** để điều hướng sang trang cập nhật thông tin sinh viên.
  - Nút **"Xóa"**:
    - Có hộp thoại xác nhận (**Confirm Dialog**) trước khi xóa.
    - Sau khi xóa thành công, điều hướng về **Trang Danh Sách** (`/students`).

### Hướng dẫn chạy dự án (local)

#### 1. Yêu cầu hệ thống

- Đã cài **JDK 17** (hoặc phiên bản tương thích với cấu hình trong `pom.xml`).
- Đã cài **Apache Maven** (nếu chưa có `mvn` trong PATH).

#### 2. Clone / tải mã nguồn

- Đặt project vào một thư mục, ví dụ: `d:\VNU-HCMUT\SE Advanced\SE-advance-252\student-management`

#### 3. Chạy bằng Maven / Maven Wrapper (development)

Trong thư mục `student-management`, mở terminal và ưu tiên chạy bằng Maven Wrapper (nếu project có file `mvnw`):

```bash
./mvnw spring-boot:run    # trên Linux/macOS
mvnw.cmd spring-boot:run  # trên Windows
```

Nếu không có Maven Wrapper, sử dụng Maven đã cài đặt sẵn:

```bash
mvn spring-boot:run
```

Sau khi build xong, ứng dụng sẽ chạy mặc định tại:

- `http://localhost:8080`

#### Câu trả lời cho phần bài tập:

##### Insert 10 sinh viên

```
INSERT INTO students (id, name, email, age) VALUES ('3', 'Vu Van C', 'C@def.com', '13');
INSERT INTO students (id, name, email, age) VALUES ('4', 'Vo Van D', 'D@def.com', '14');
INSERT INTO students (id, name, email, age) VALUES ('5', 'Nguyen Van E', 'E@def.com', '15');
INSERT INTO students (id, name, email, age) VALUES ('6', 'Ta Thi F', 'F@def.com', '16');
INSERT INTO students (id, name, email, age) VALUES ('7', 'Ai Tan Giac La G', 'G@def.com', '17');
INSERT INTO students (id, name, email, age) VALUES ('8', 'Alex H', 'H@def.com', '18');
INSERT INTO students (id, name, email, age) VALUES ('9', 'Do Tuan I', 'I@def.com', '19');
INSERT INTO students (id, name, email, age) VALUES ('10', 'Le Minh K', 'K@def.com', '20');
INSERT INTO students (id, name, email, age) VALUES ('11', 'Phung Thanh L', 'L@def.com', '21');
INSERT INTO students (id, name, email, age) VALUES ('12', 'Truong Tuan M', 'M@def.com', '22');
```

##### Khi thêm 1 sinh viên có trùng khóa chính với người đã có sẵn:

ID của sinh viên được đặt làm khóa chính (Primary Key). Vì vậy, khi cố tình insert một sinh viên có ID trùng với ID đã tồn tại trong database, thao tác này sẽ vi phạm ràng buộc khóa chính duy nhất (UNIQUE constraint). Do đó, hệ quản trị cơ sở dữ liệu sẽ chặn thao tác insert và trả về lỗi UNIQUE constraint failed để đảm bảo tính toàn vẹn dữ liệu.

##### Khi insert tên 1 sinh viên nhưng bỏ trống cột name:

Do schema không quy định cột name là `NOT NULL`, nên khi insert một sinh viên với giá trị name là `NULL`, database sẽ không báo lỗi. Tuy nhiên, về mặt toàn vẹn dữ liệu, điều này gây ra khiếm khuyết vì một record không có đầy đủ thông tin cần thiết. Khi code Java truy vấn dữ liệu lên, các bản ghi thiếu tên sẽ gây khó khăn trong xử lý logic và hiển thị, thậm chí có thể dẫn đến lỗi ở tầng ứng dụng.

##### Cấu hình Hibernate:

Nguyên nhân khiến dữ liệu trong database bị mất mỗi khi tắt ứng dụng và chạy lại là do cấu hình Hibernate được đặt là `spring.jpa.hibernate.ddl-auto=create`, cấu hình này có nghĩa là mỗi lần ứng dụng khởi động, Hibernate sẽ xóa toàn bộ bảng cũ và tạo lại schema mới, dẫn đến việc dữ liệu bị reset hoàn toàn. Trong môi trường thực tế (production), cấu hình này thường được đổi thành `update` hoặc `none` để tránh mất dữ liệu. Vì vậy, dữ liệu trong database bị tự động reset sau mỗi lần restart ứng dụng.

### Screenshot các module được yêu cầu của Lab 4

#### Trang danh sách:
<img width="1920" height="1080" alt="Screenshot (1335)" src="https://github.com/user-attachments/assets/0d55dfd6-be86-4539-a577-0afe8123ff1b" />
#### Trang chi tiết
<img width="1920" height="1080" alt="Screenshot (1336)" src="https://github.com/user-attachments/assets/a26aee40-b473-4307-8a19-97171bfc3ada" />
#### Thêm mới
<img width="1920" height="1080" alt="Screenshot (1338)" src="https://github.com/user-attachments/assets/9d090f6a-dca2-42c8-9053-f644bd61ffa9" />
#### Chỉnh sửa
<img width="1920" height="1080" alt="Screenshot (1337)" src="https://github.com/user-attachments/assets/517c56dd-f36d-4d4f-b4a0-9d606ff61e5d" />

