// Package tempat file disimpan
package QUEUE;

// Kelas MyQueue untuk membuat struktur data Queue berbasis array
class MyQueue {
    int maxSize;   // Kapasitas maksimum array
    int nItem;     // Jumlah elemen yang sedang ada di queue
    int front;     // Indeks elemen paling depan
    int rear;      // Indeks elemen paling belakang
    int[] array;   // Array penyimpanan data queue

    // Konstruktor untuk inisialisasi queue
    public MyQueue(int maxSize) {
        this.maxSize = maxSize;    // Tentukan ukuran maksimum queue
        rear = -1;                 // Awalnya tidak ada elemen di belakang
        front = 0;                 // Awal posisi depan di index 0
        nItem = 0;                 // Belum ada data di queue
        array = new int[maxSize];  // Buat array sesuai kapasitas
    }

    // Menambahkan data ke belakang queue
    public void insert(int data) {
        if (!isFull()) {                // Cek apakah queue belum penuh
            if (rear == maxSize - 1)    // Jika indeks belakang mencapai akhir array
                rear = -1;              // Kembalikan ke awal (sistem circular)
            array[++rear] = data;       // Naikkan rear, simpan data
            nItem++;                    // Tambah jumlah elemen
        }
    }

    // Menghapus data dari depan queue
    public int delete() {
        if (!isEmpty()) {               // Cek apakah queue tidak kosong
            nItem--;                    // Kurangi jumlah elemen
            return array[front++];      // Ambil data paling depan, geser front ke depan
        }
        return -1;                      // Jika kosong, kembalikan -1
    }

    // Mengecek apakah queue kosong
    public boolean isEmpty() {
        return nItem == 0;              // True jika tidak ada elemen
    }

    // Mengecek apakah queue penuh
    public boolean isFull() {
        return nItem == maxSize;        // True jika jumlah elemen = kapasitas
    }

    // Menampilkan seluruh elemen dalam queue
    public void display() {
        if (nItem > 0) {                // Hanya tampilkan jika ada data
            if (front <= rear) {        // Jika posisi front sebelum rear (normal)
                for (int i = front; i <= rear; i++)
                    System.out.print(array[i] + " "); // Cetak data satu per satu
            } else {                    // Jika posisi front melewati rear (circular)
                for (int i = front; i < maxSize; i++)
                    System.out.print(array[i] + " "); // Cetak dari front ke akhir array
                for (int i = 0; i <= rear; i++)
                    System.out.print(array[i] + " "); // Lanjut dari awal array ke rear
            }
        }
    }
}

// Kelas utama untuk menjalankan program Queue
public class DemoQueue {
    public static void main(String[] args) {

        MyQueue my = new MyQueue(3);  // Buat queue berkapasitas 3

        my.insert(5);    // Tambahkan data 5
        my.insert(10);   // Tambahkan data 10
        my.insert(20);   // Tambahkan data 20 (queue penuh)

        my.delete();     // Hapus elemen depan (5)
        my.delete();     // Hapus elemen depan (10)

        my.insert(100);  // Tambah data baru 100 (mengisi posisi kosong circular)

        my.display();    // Tampilkan isi queue → output: 20 100
    }
}
