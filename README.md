# Reflection 1

Setelah menyelesaikan fitur untuk membuat, melihat, edit, dan delete produk dalam struktur aplikasi yang dibagi menjadi 4 bagian (repository, service, controller, dan model), saya merasa aplikasi menjadi lebih terorganisir dan mudah dipahami. Setiap bagian memiliki tanggung jawab yang jelas dan saling terhubung dengan yang lain. 

Bagian model berfungsi untuk mendefinisikan struktur data yang akan digunakan dalam aplikasi, sedangkan bagian repository berfungsi untuk menghubungkan model dengan database, bagian service berfungsi untuk mengelola logika bisnis, dan bagian controller berfungsi untuk mengelola permintaan dari user.

Pengerjaan setiap fitur dengan branch github yang berbeda membiasakan saya untuk bekerja dengan git dan github dengan lebih efektif, dan memudahkan saya untuk mengelola kode dengan lebih terstruktur.

Membaca kode yang telah saya buat, beberapa bagian kode masih memerlukan peningkatan, misalnya validasi input data yang lebih rapi dan detail, juga memberikan popup alert ketika berhasil atau gagal melakukan operasi CRUD. Terlebih lagi, dibutuhkan juga testing untuk memastikan bahwa aplikasi bekerja dengan benar.

---

# Reflection 2

1. Setelah menulis unit test, saya merasa lebih yakin dan percaya diri dengan kebenaran kode yang telah saya buat. Unit test berfungsi sebagai jaring pengaman yang dapat menangkap bug sejak awal dan memastikan bahwa setiap komponen bekerja sesuai dengan yang diharapkan.

   Menurut saya, tidak ada jumlah pasti berapa unit test yang harus dibuat dalam satu class. Yang penting adalah setiap method publik memiliki test yang mencakup skenario-skenario yang bermakna, seperti kondisi normal, edge cases, dan kondisi error. Jumlah test sebaiknya disesuaikan dengan kompleksitas method yang diuji.

   Untuk memastikan unit test sudah cukup, kita bisa menggunakan metrik code coverage yang mengukur seberapa banyak kode sumber yang dieksekusi selama pengujian. Metrik ini membantu kita melihat bagian kode mana yang belum tercover oleh test. Namun, meskipun kita mencapai 100% code coverage, bukan berarti kode kita bebas dari bug. Coverage hanya menunjukkan bahwa setiap baris kode pernah dijalankan, tapi tidak menjamin bahwa semua kemungkinan input atau edge case sudah diuji. Bug masih bisa tersembunyi melalui kesalahan logika atau kondisi yang tidak terduga.

2. Jika saya membuat class functional test baru dengan setup procedures dan instance variables yang sama seperti CreateProductFunctionalTest.java, menurut saya hal tersebut akan mengurangi kualitas kode karena terjadi duplikasi. 

   Masalah clean code yang teridentifikasi adalah pelanggaran prinsip DRY (Don't Repeat Yourself). Field seperti serverPort, testBaseUrl, dan baseUrl akan diulang di setiap class test. Method setup dengan anotasi BeforeEach juga akan diduplikasi. Hal ini sangat tidak efisien dan tidak sesuai dengan prinsip clean code.

   Untuk memperbaiki masalah ini, saya menyarankan pembuatan base class yang berisi semua setup procedures dan instance variables yang sama. Class-class functional test lainnya dapat melakukan extends dari base class tersebut. Dengan pendekatan ini, duplikasi kode berkurang, maintenance menjadi lebih mudah, dan jika ada perubahan cukup dilakukan di satu tempat saja.