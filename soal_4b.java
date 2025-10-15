package GRAPH; // Menyimpan file dalam package bernama GRAPH

import java.util.*; // Mengimpor library untuk Stack dan Queue

// ================= CLASS VERTEX =================
class Vertex {
    String label;     // Menyimpan nama simpul (contoh: A, B, C)
    boolean visited;  // Menandai apakah simpul sudah dikunjungi

    // Konstruktor untuk inisialisasi label simpul
    public Vertex(String label) {
        this.label = label;   // Menetapkan nama simpul
        this.visited = false; // Awalnya belum dikunjungi
    }
}

// ================= CLASS GRAPH =================
class MyGraph {
    Vertex[] vertices;   // Array untuk menyimpan semua simpul
    int[][] matrix;      // Matriks adjacency (hubungan antar simpul)
    int nVertex;         // Menyimpan jumlah simpul saat ini

    Stack<Integer> stack = new Stack<>();       // Struktur data stack untuk DFS
    Queue<Integer> queue = new LinkedList<>();  // Struktur data queue untuk BFS

    // Konstruktor untuk inisialisasi graph
    public MyGraph(int max) {
        vertices = new Vertex[max];   // Menentukan kapasitas maksimal simpul
        matrix = new int[max][max];   // Membuat matriks kosong ukuran max x max
        nVertex = 0;                  // Awalnya belum ada simpul
    }

    // ================= TAMBAH SIMPUL =================
    public void addVertex(String label) {
        vertices[nVertex++] = new Vertex(label); // Tambahkan simpul baru ke array dan tingkatkan jumlah simpul
    }

    // ================= TAMBAH SISI =================
    public void addEdge(int start, int end) {
        matrix[start][end] = 1; // Tandai ada hubungan dari simpul start ke end
        matrix[end][start] = 1; // Karena graph tidak berarah, arah sebaliknya juga 1
    }

    // ================= TAMPILKAN MATRIKS =================
    public void displayMatrix() {
        System.out.print("\t"); // Cetak tab untuk perataan header
        for (int i = 0; i < nVertex; i++) // Cetak label kolom
            System.out.print(vertices[i].label + "\t");
        System.out.println(); // Pindah baris

        // Cetak isi matriks adjacency
        for (int i = 0; i < nVertex; i++) {
            System.out.print(vertices[i].label + "\t"); // Cetak label baris
            for (int j = 0; j < nVertex; j++) {
                System.out.print(matrix[i][j] + "\t");  // Cetak nilai hubungan (1 atau 0)
            }
            System.out.println(); // Pindah baris setelah satu baris selesai
        }
    }

    // ================= CARI TETANGGA YANG BELUM DIKUNJUNGI =================
    public int getAdjVertex(int v) {
        for (int i = 0; i < nVertex; i++) { // Cek semua simpul
            if (matrix[v][i] == 1 && !vertices[i].visited) // Jika ada hubungan dan belum dikunjungi
                return i; // Kembalikan index simpul tersebut
        }
        return -1; // Jika tidak ada simpul tetangga yang belum dikunjungi
    }

    // ================= TRAVERSAL DFS =================
    public void dfs() {
        System.out.print("DFS: "); // Cetak judul traversal
        stack.push(0); // Masukkan simpul pertama (index 0) ke stack
        vertices[0].visited = true; // Tandai simpul pertama sebagai dikunjungi
        System.out.print(vertices[0].label + " "); // Cetak label simpul pertama

        // Ulangi selama masih ada simpul di stack
        while (!stack.isEmpty()) {
            int v = getAdjVertex(stack.peek()); // Ambil tetangga dari simpul paling atas di stack
            if (v == -1) { // Jika tidak ada tetangga lagi
                stack.pop(); // Keluarkan simpul dari stack (mundur)
            } else {
                vertices[v].visited = true; // Tandai simpul tetangga sebagai dikunjungi
                System.out.print(vertices[v].label + " "); // Cetak label simpul
                stack.push(v); // Masukkan simpul ke stack (lanjut eksplorasi)
            }
        }
        resetVisited(); // Reset semua simpul agar bisa dipakai lagi
    }

    // ================= TRAVERSAL BFS =================
    public void bfs() {
        System.out.print("BFS: "); // Cetak judul traversal
        queue.add(0); // Masukkan simpul pertama (index 0) ke queue
        vertices[0].visited = true; // Tandai simpul pertama sebagai dikunjungi
        System.out.print(vertices[0].label + " "); // Cetak label simpul pertama

        // Ulangi selama queue tidak kosong
        while (!queue.isEmpty()) {
            int v1 = queue.remove(); // Keluarkan simpul dari antrian
            int v2; // Variabel untuk menyimpan tetangga
            while ((v2 = getAdjVertex(v1)) != -1) { // Cari tetangga yang belum dikunjungi
                vertices[v2].visited = true; // Tandai simpul tetangga
                System.out.print(vertices[v2].label + " "); // Cetak label tetangga
                queue.add(v2); // Masukkan tetangga ke queue untuk dieksplorasi nanti
            }
        }
        resetVisited(); // Reset semua simpul agar traversal berikutnya bisa dilakukan lagi
    }

    // ================= RESET STATUS VISITED =================
    private void resetVisited() {
        for (int i = 0; i < nVertex; i++) // Loop semua simpul
            vertices[i].visited = false;  // Tandai semuanya belum dikunjungi
    }
}

// ================= CLASS MAIN (DEMO) =================
public class DemoGraph {
    public static void main(String[] args) {
        MyGraph g = new MyGraph(10); // Buat graph dengan kapasitas maksimal 10 simpul

        // Tambah simpul dengan label A-E
        g.addVertex("A"); // Index 0
        g.addVertex("B"); // Index 1
        g.addVertex("C"); // Index 2
        g.addVertex("D"); // Index 3
        g.addVertex("E"); // Index 4

        // Tambah sisi (hubungan antar simpul)
        g.addEdge(0, 1); // Hubungkan A-B
        g.addEdge(0, 3); // Hubungkan A-D
        g.addEdge(1, 4); // Hubungkan B-E
        g.addEdge(2, 4); // Hubungkan C-E
        g.addEdge(3, 4); // Hubungkan D-E

        // Tampilkan matriks adjacency
        g.displayMatrix();

        // Jalankan traversal DFS
        g.dfs();
        System.out.println(); // Baris baru

        // Jalankan traversal BFS
        g.bfs();
        System.out.println(); // Baris baru
    }
}
