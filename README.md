reflection 1
# Reflection on Implemented Features

Pada modul ini, saya telah berhasil mengimplementasikan dua fitur baru menggunakan Spring Boot. Fitur-fitur tersebut adalah:

1. **Menambah Produk (Create Product)**
2. **Mengedit Produk (Edit Product)**

Setelah meninjau kembali kode sumber saya, berikut adalah beberapa prinsip *clean code* dan praktik *secure coding* yang telah diterapkan dalam kode saya:

### Prinsip Clean Code yang Diterapkan:
1. **Penamaan yang Jelas dan Deskriptif**  
   Variabel, metode, dan kelas memiliki nama yang jelas dan mudah dipahami. Misalnya, nama metode seperti `createProduct`, `editProduct`, dan `deleteProduct` menggambarkan dengan jelas tindakan yang dilakukan oleh masing-masing metode.

2. **Konsistensi**  
   Struktur kode saya konsisten, baik dari segi penamaan, indentasi, maupun penggunaan anotasi di Spring Boot. Ini membantu agar kode tetap mudah dibaca dan dipahami oleh orang lain.

3. **Penggunaan Kelas yang Kecil dan Terfokus**  
   Kelas-kelas yang digunakan, seperti `ProductController`, hanya berfokus pada satu tanggung jawab, yaitu menangani permintaan terkait produk. Tidak ada kelas yang terlalu besar atau kompleks.

4. **Tidak Ada Pengulangan Kode**  
   Saya berusaha untuk menghindari pengulangan kode yang tidak perlu. Misalnya, proses pembuatan dan pengeditan produk hampir identik, tetapi saya telah memisahkan logika ini dengan menggunakan metode yang terpisah dan pendekatan yang efisien.

5. **Penggunaan Dependency Injection**  
   Saya menggunakan `@Autowired` untuk menginjeksi dependensi, yang membuat kode lebih modular dan mudah diuji.

### Praktik Secure Coding yang Diterapkan:
1. **Validasi Input Pengguna**  
   Walaupun tidak ada validasi eksplisit yang ditambahkan dalam kode ini, untuk meningkatkan keamanan, kita bisa menambahkan validasi untuk memastikan data yang dimasukkan pengguna valid (misalnya, memastikan bahwa `productQuantity` lebih besar dari nol). Hal ini akan mencegah serangan seperti *SQL Injection* atau data yang tidak valid masuk ke sistem.

2. **Pemisahan Data Sensitif**  
   Data sensitif (seperti password, jika ada) harus dipisahkan dan dienkripsi. Dalam implementasi ini, saya belum menangani data sensitif secara eksplisit, namun untuk keamanan tambahan, data sensitif harus selalu dienkripsi dan diakses dengan kontrol yang ketat.

3. **Handling Error yang Baik**  
   Saya menggunakan redirect untuk halaman tertentu setelah operasi berhasil atau gagal, seperti redirect ke daftar produk setelah mengedit atau menghapus produk. Ini menghindari pengungkapan informasi internal di halaman error.

4. **Menggunakan HTTPS (Disarankan)**  
   Untuk aplikasi yang akan digunakan dalam produksi, sangat disarankan untuk menggunakan HTTPS untuk komunikasi yang lebih aman antara klien dan server.

### Perbaikan yang Dapat Dilakukan:
1. **Validasi Input Pengguna**  
   Salah satu perbaikan yang bisa dilakukan adalah menambahkan validasi pada input produk. Misalnya, sebelum menambahkan atau mengedit produk, pastikan bahwa data yang dimasukkan pengguna adalah valid (misalnya, tidak ada nama produk yang kosong atau kuantitas produk yang kurang dari 1).

2. **Menambahkan Exception Handling yang Lebih Baik**  
   Saat ini, kode ini tidak menangani kesalahan dengan cara yang sangat terperinci. Misalnya, jika produk tidak ditemukan dalam database, sebaiknya menambahkan penanganan pengecualian yang lebih informatif agar pengguna tahu apa yang salah.

3. **Penerapan Spring Security**  
   Untuk aplikasi yang lebih aman, implementasi autentikasi dan otorisasi pengguna dengan menggunakan Spring Security akan meningkatkan keamanan aplikasi secara signifikan.

---

Dengan penerapan prinsip-prinsip tersebut, kode saya menjadi lebih mudah dibaca, lebih aman, dan lebih terstruktur. Tentunya, masih banyak ruang untuk perbaikan, terutama dalam hal validasi input dan penanganan kesalahan. Saya akan terus mengembangkan kemampuan saya untuk menulis kode yang lebih bersih dan aman di masa depan.

reflection 4


1. Refleksi Mengenai Alur TDD
Berdasarkan pengalaman saya dalam mengikuti alur TDD pada exercise ini, saya merasa alur tersebut sangat berguna, terutama dalam membantu saya menjaga fokus pada fungsi yang sedang dikembangkan dan memastikan bahwa setiap bagian kode yang saya buat telah teruji dengan baik. Namun, pada beberapa titik, saya merasa bahwa alur TDD bisa terasa sedikit berulang-ulang dan terkadang memakan waktu lebih lama daripada hanya menulis kode langsung tanpa tes terlebih dahulu.

Namun, saya memahami bahwa TDD membantu saya memastikan bahwa kode yang saya buat bebas dari bug sejak awal dan memastikan bahwa setiap fitur yang saya buat memiliki unit test yang sesuai. Saya juga belajar pentingnya menulis tes yang sederhana dan jelas, yang sangat membantu saat melakukan debugging.

Jika saya merasa alur ini kurang efektif di masa depan, saya mungkin perlu lebih mengoptimalkan proses menulis tes dengan memperhatikan kesederhanaan tes dan mencoba untuk lebih fokus pada penulisan tes yang mudah dipahami dan mengurangi kompleksitas dalam langkah-langkah pengujian.

2. Refleksi Mengenai Prinsip F.I.R.S.T. pada Tes Saya
Mengenai prinsip F.I.R.S.T. (Fast, Independent, Repeatable, Self-validating, Timely), saya merasa bahwa sebagian besar tes yang saya buat sudah memenuhi prinsip-prinsip tersebut, meskipun ada beberapa area yang bisa saya tingkatkan.

Fast: Tes yang saya buat umumnya cepat dijalankan, namun terkadang ada tes yang agak lambat karena bergantung pada data yang besar atau kompleks.
Independent: Sebagian besar tes saya independen, meskipun ada beberapa tes yang secara tidak sengaja terhubung karena ketergantungan antar modul.
Repeatable: Tes yang saya buat sudah bisa dijalankan berulang kali tanpa masalah.
Self-validating: Saya merasa sebagian besar tes saya dapat memberikan hasil yang jelas dan mudah dipahami, namun ada kalanya hasil tesnya membutuhkan interpretasi manual.
Timely: Saya merasa sudah cukup cepat dalam menulis tes sesuai dengan kebutuhan fitur yang dikembangkan, namun terkadang proses menulis tes bisa lebih efisien.
Jika saya tidak sepenuhnya mengikuti prinsip F.I.R.S.T., hal yang perlu saya lakukan adalah memastikan bahwa tes yang saya buat lebih independen dan tidak saling bergantung, serta lebih memperhatikan untuk membuat tes lebih cepat dan mudah dipahami.


