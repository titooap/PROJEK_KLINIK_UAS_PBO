# **KlinikPro - Sistem Manajemen Klinik Sederhana 🏥**

## **📋 Deskripsi Project**
**KlinikPro** adalah aplikasi manajemen klinik berbasis Java yang dibangun menggunakan konsep Object-Oriented Programming (OOP) dengan GUI yang user-friendly. Aplikasi ini dirancang untuk meningkatkan efisiensi dan efektivitas pengelolaan operasional klinik, mulai dari administrasi pasien hingga manajemen sumber daya medis.

## **🛠️ Teknologi yang Digunakan**
* **Java** - Bahasa pemrograman utama dengan dukungan multiplatform
* **Java Swing/JavaFX** - Library GUI untuk antarmuka pengguna yang intuitif
* **Object-Oriented Programming** - Paradigma pemrograman dengan konsep encapsulation, inheritance, polymorphism, dan abstraction
* **UML (Unified Modeling Language)** - Untuk perancangan dan dokumentasi sistem

## **🧩 Komponen Utama**

### **1. Sistem Autentikasi Multi-Role**
* Login terpisah untuk Admin dan Dokter
* Validasi kredensial dengan pengecekan role
* Dashboard yang disesuaikan berdasarkan hak akses pengguna

### **2. Manajemen Data Pasien**
* Pendaftaran pasien baru dengan data lengkap (nama, umur, alamat, nomor kontak)
* Sistem CRUD (Create, Read, Update, Delete) untuk data pasien
* Pencatatan keluhan awal dan riwayat medis

### **3. Pengelolaan Sumber Daya Medis**
* **Manajemen Dokter**: Data dokter, spesialisasi, dan informasi kontak
* **Katalog Obat**: Daftar obat dengan informasi stok, jenis, dan harga
* **Manajemen Ruangan**: Pengaturan ruang periksa, ruang tunggu, dan ruang perawatan

### **4. Sistem Penjadwalan**
* Jadwal praktik dokter dengan pengaturan hari, jam mulai, dan jam berakhir
* Alokasi ruangan untuk setiap sesi praktik
* Tampilan jadwal yang mudah diakses oleh admin dan dokter

### **5. Sistem Resep Digital**
* Pembuatan resep obat oleh dokter
* Pencatatan diagnosis dan dosis obat
* Riwayat resep pasien yang dapat diakses dokter dan admin

## **👥 Aktor Sistem**

### **Admin**
* **Kelola Data**: Pendaftaran dan pengelolaan data pasien, dokter, perawat, dan obat
* **Lihat Data**: Akses ke semua informasi sistem (list pasien, dokter, jadwal, resep)
* **Manajemen Sistem**: Kontrol penuh terhadap operasional sistem

### **Dokter**
* **Jadwal Praktik**: Melihat dan mengelola jadwal pribadi
* **Resep Pasien**: Membuat dan melihat resep yang telah dibuat
* **Data Pasien**: Akses informasi pasien untuk keperluan medis

## **🎯 Keunggulan Sistem**
* **Modular Design**: Implementasi OOP memungkinkan pengembangan dan maintenance yang mudah
* **User-Friendly Interface**: GUI yang intuitif untuk semua level pengguna
* **Comprehensive Features**: Mencakup semua aspek operasional klinik dalam satu sistem
* **Data Integrity**: Sistem pencatatan yang akurat dan terpusat
* **Role-Based Access**: Keamanan data dengan pembagian akses sesuai peran

## **🚀 Manfaat Implementasi**
* Mengurangi kesalahan pencatatan manual
* Meningkatkan efisiensi pelayanan pasien
* Memudahkan koordinasi antara admin dan dokter
* Dokumentasi yang lebih baik dan terstruktur
* Mendukung kelancaran operasional klinik sehari-hari

## **📊 Dokumentasi Teknis**
Sistem ini dilengkapi dengan dokumentasi UML lengkap meliputi:
* **Class Diagram** - Struktur kelas dan relasi antar objek
* **Use Case Diagram** - Interaksi aktor dengan sistem
* **Activity Diagram** - Alur proses bisnis
* **Sequence Diagram** - Interaksi antar komponen sistem

---
*Dikembangkan oleh Kelompok 2 - Kelas 4B Sistem Informasi*  
*Universitas Singaperbangsa Karawang 2025*
