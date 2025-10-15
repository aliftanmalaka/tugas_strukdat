// Package tempat file disimpan
package DOUBLELINKEDLIST;

// Kelas Node sebagai elemen dari double linked list
class Node {
    int data;      // Menyimpan data
    Node next;     // Menunjuk ke node berikutnya
    Node prev;     // Menunjuk ke node sebelumnya

    // Konstruktor untuk mengisi nilai data
    public Node(int data) {
        this.data = data;
    }
}

// Kelas MyDouble berisi operasi double linked list
class MyDouble {
    Node first;    // Menunjuk node pertama
    Node last;     // Menunjuk node terakhir
    int size = 0;  // Menyimpan jumlah elemen

    // Menambah elemen di awal list
    public void addFirst(int data) {
        Node newNode = new Node(data);     // Buat node baru
        if (first == null) {               // Jika list kosong
            first = newNode;               // Node baru jadi node pertama
            last = newNode;                // Sekaligus node terakhir
        } else {                           // Jika sudah ada isi
            newNode.next = first;          // Hubungkan node baru ke node pertama
            first.prev = newNode;          // Hubungkan node pertama ke node baru
            first = newNode;               // Jadikan node baru sebagai yang pertama
        }
        size++;                            // Tambah jumlah data
    }

    // Menambah elemen di akhir list
    public void addLast(int data) {
        Node newNode = new Node(data);     // Buat node baru
        if (first == null) {               // Jika list kosong
            first = last = newNode;        // Node baru jadi node pertama & terakhir
        } else {
            last.next = newNode;           // Hubungkan node terakhir ke node baru
            newNode.prev = last;           // Hubungkan node baru ke node terakhir
            last = newNode;                // Update node terakhir
        }
        size++;                            // Tambah jumlah data
    }

    // Menampilkan semua data
    public void printAll() {
        Node current = first;              // Mulai dari node pertama
        while (current != null) {          // Selama belum null
            System.out.print(current.data + " "); // Cetak data
            current = current.next;        // Pindah ke node berikutnya
        }
    }

    // Menghapus elemen pertama
    public void removeFirst() {
        first = first.next;                // Geser pointer ke node kedua
        if (first != null)                 // Jika masih ada data
            first.prev = null;             // Putuskan hubungan ke node lama
        size--;                            // Kurangi ukuran
    }

    // Menghapus elemen terakhir
    public void removeLast() {
        last = last.prev;                  // Geser pointer ke node sebelumnya
        if (last != null)                  // Jika masih ada data
            last.next = null;              // Putuskan hubungan ke node lama
        size--;                            // Kurangi ukuran
    }

    // Mengecek apakah list kosong
    public boolean isEmpty() {
        return first == null;
    }

    // Mendapatkan jumlah elemen
    public int getSize() {
        return size;
    }

    // Menghapus node berdasarkan nilai data (key)
    public void remove(int key) {
        if (key == first.data) {           // Jika data di node pertama
            removeFirst();
        } else if (key == last.data) {     // Jika data di node terakhir
            removeLast();
        } else {
            Node current = first.next;     // Mulai dari node kedua
            while (current != null) {      // Telusuri hingga akhir
                if (key == current.data) { // Jika ditemukan
                    current.prev.next = current.next; // Sambungkan node sebelum & sesudahnya
                    current.next.prev = current.prev;
                    size--;                // Kurangi ukuran
                    break;                 // Hentikan loop
                }
                current = current.next;    // Lanjutkan ke node berikutnya
            }
        }
    }

    // Menghapus semua data
    public void removeAll() {
        first = null;                      // Hilangkan referensi node pertama
        last = null;                       // Hilangkan referensi node terakhir
        size = 0;                          // Reset ukuran
    }

    // Mencari data tertentu (mengembalikan true jika ditemukan)
    public boolean search(int key) {
        Node current = first;              // Mulai dari node pertama
        while (current != null) {          // Telusuri semua node
            if (current.data == key) {     // Jika data cocok
                return true;
            }
            current = current.next;        // Lanjut ke node berikutnya
        }
        return false;                      // Jika tidak ditemukan
    }

    // Mengambil data berdasarkan indeks
    public int indexOf(int index) {
        Node current = first;              // Mulai dari node pertama
        for (int i = 0; i < index; i++) {  // Ulangi sampai posisi index
            current = current.next;
        }
        return current.data;               // Kembalikan data
    }

    // Mendapatkan posisi (indeks) suatu data
    public int get(int key) {
        int result = 0;                    // Variabel penghitung indeks
        Node current = first;              // Mulai dari node pertama
        while (current != null) {          // Telusuri semua node
            if (current.data == key) {     // Jika data cocok
                return result;             // Kembalikan posisi
            }
            current = current.next;        // Lanjut ke node berikutnya
            result++;                      // Tambah posisi
        }
        return -1;                         // Jika tidak ditemukan
    }
}

// Kelas utama untuk menjalankan program
public class DemoDouble {
    public static void main(String[] args) {
        MyDouble my = new MyDouble();      // Buat objek linked list

        // Tambahkan data di awal list
        my.addFirst(40);
        my.addFirst(30);
        my.addFirst(20);
        my.addFirst(10);

        // Tambahkan data di akhir list
        my.addLast(50);

        // Cetak semua data
        my.printAll();                     // Output: 10 20 30 40 50
    }
}
