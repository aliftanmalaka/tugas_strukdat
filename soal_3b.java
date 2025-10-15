package BST; // Menentukan package tempat file disimpan

// ================= CLASS NODE =================
class Node {
    int data;      // Menyimpan nilai data pada node
    Node right;    // Menunjuk ke node anak kanan
    Node left;     // Menunjuk ke node anak kiri

    // Konstruktor untuk membuat node baru dengan nilai data
    public Node(int data) {
        this.data = data; // Inisialisasi data node
    }
}

// ================= CLASS MyBST =================
class MyBST {
    Node root; // Menyimpan node akar dari Binary Search Tree

    // -------------------- INSERT --------------------
    public void insert(int key) {                // Menambahkan node baru
        Node newNode = new Node(key);            // Buat node baru dengan nilai key
        if (root == null) {                      // Jika tree kosong
            root = newNode;                      // Node baru menjadi root
        } else {                                 // Jika tree sudah ada isinya
            Node current = root;                 // Mulai dari root
            while (true) {                       // Ulangi sampai menemukan posisi yang sesuai
                if (key > current.data) {        // Jika nilai lebih besar dari current
                    if (current.right == null) { // Jika tidak ada anak kanan
                        current.right = newNode; // Tempatkan di kanan
                        break;                   // Keluar loop
                    }
                    current = current.right;     // Jika kanan ada, lanjut ke kanan
                } else {                         // Jika nilai lebih kecil atau sama
                    if (current.left == null) {  // Jika tidak ada anak kiri
                        current.left = newNode;  // Tempatkan di kiri
                        break;                   // Keluar loop
                    }
                    current = current.left;      // Jika kiri ada, lanjut ke kiri
                }
            }
        }
    }

    // -------------------- FIND --------------------
    public boolean find(int key) {               // Mencari nilai tertentu
        boolean result = false;                  // Hasil pencarian (true jika ditemukan)
        Node current = root;                     // Mulai dari root
        while (current != null) {                // Telusuri selama node belum null
            if (key == current.data) {           // Jika data cocok
                result = true;                   // Tandai ditemukan
                break;                           // Hentikan pencarian
            } else if (key > current.data) {     // Jika lebih besar, geser ke kanan
                current = current.right;
            } else {                             // Jika lebih kecil, geser ke kiri
                current = current.left;
            }
        }
        return result;                           // Kembalikan hasil pencarian
    }

    // -------------------- GET SUCCESSOR --------------------
    public Node getSuccesor(Node del) {          // Mendapatkan node pengganti (successor)
        Node current = del.right;                // Mulai dari anak kanan node yang akan dihapus
        while (current.left != null) {           // Selama masih punya anak kiri
            current = current.left;              // Bergerak ke node paling kiri
        }
        return current;                          // Kembalikan node successor
    }

    // -------------------- INORDER TRAVERSAL --------------------
    public void inOrder(Node root) {             // Menampilkan data secara inorder
        if (root != null) {                      // Jika node tidak null
            inOrder(root.left);                  // Kunjungi anak kiri
            System.out.print(root.data + " ");   // Cetak data node
            inOrder(root.right);                 // Kunjungi anak kanan
        }
    }

    // -------------------- DELETE --------------------
    public void delete(int key) {                // Menghapus node berdasarkan nilai
        Node current = root;                     // Mulai dari root
        Node parent = root;                      // Simpan parent dari node
        boolean right = false;                   // Menandakan node di sisi kanan atau kiri parent

        // Cari node yang ingin dihapus
        while (key != current.data) {            // Selama belum ketemu
            parent = current;                    // Simpan node parent
            if (key > current.data) {            // Jika nilai lebih besar
                right = true;                    // Tandai di sisi kanan
                current = current.right;         // Geser ke anak kanan
            } else {                             // Jika nilai lebih kecil
                right = false;                   // Tandai di sisi kiri
                current = current.left;          // Geser ke anak kiri
            }
        }

        // ==== KASUS 1: Node tidak punya anak ====
        if (current.right == null && current.left == null) {
            if (current == root) {               // Jika node adalah root
                root = null;                     // Hapus seluruh tree
            } else if (right) {                  // Jika node di kanan parent
                parent.right = null;             // Putuskan hubungan kanan
            } else {                             // Jika node di kiri parent
                parent.left = null;              // Putuskan hubungan kiri
            }
        }

        // ==== KASUS 2: Node hanya punya anak kanan ====
        else if (current.right != null && current.left == null) {
            if (current == root) {               // Jika node adalah root
                root = current.right;            // Ganti root dengan anak kanan
            } else if (right) {                  // Jika di kanan parent
                parent.right = current.right;    // Hubungkan parent dengan anak kanan
            } else {                             // Jika di kiri parent
                parent.left = current.right;     // Hubungkan parent dengan anak kanan
            }
        }

        // ==== KASUS 3: Node hanya punya anak kiri ====
        else if (current.left != null && current.right == null) {
            if (current == root) {               // Jika node adalah root
                root = current.left;             // Ganti root dengan anak kiri
            } else if (right) {                  // Jika di kanan parent
                parent.right = current.left;     // Hubungkan parent dengan anak kiri
            } else {                             // Jika di kiri parent
                parent.left = current.left;      // Hubungkan parent dengan anak kiri
            }
        }

        // ==== KASUS 4: Node punya dua anak ====
        else {
            Node successor = getSuccesor(current); // Ambil node pengganti (successor)
            delete(successor.data);                // Hapus successor dari posisi lamanya
            if (current == root) {                 // Jika node adalah root
                root.data = successor.data;        // Ganti nilai root dengan successor
            } else if (right) {                    // Jika node di kanan parent
                parent.right.data = successor.data;// Ganti nilai parent kanan
            } else {                               // Jika node di kiri parent
                parent.left.data = successor.data; // Ganti nilai parent kiri
            }
        }
    }
}

// ================= CLASS DEMO (MAIN) =================
public class DemoBST {
    public static void main(String[] args) {
        MyBST my = new MyBST();          // Buat objek tree baru bernama my

        my.insert(50);                   // Tambahkan node dengan nilai 50
        my.insert(40);                   // Tambahkan node dengan nilai 40
        my.insert(45);                   // Tambahkan node dengan nilai 45
        my.insert(80);                   // Tambahkan node dengan nilai 80
        my.insert(90);                   // Tambahkan node dengan nilai 90
        my.insert(95);                   // Tambahkan node dengan nilai 95
        my.insert(85);                   // Tambahkan node dengan nilai 85

        my.delete(80);                   // Hapus node dengan nilai 80
        my.inOrder(my.root);             // Tampilkan hasil traversal inorder
    }
}
