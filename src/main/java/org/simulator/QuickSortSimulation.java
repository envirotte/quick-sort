package org.simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*data.Node = package buatan sendiri*/

class Petunjuk extends JFrame
{
    private JTextArea area;

    public Petunjuk()
    {
        super("Petunjuk penggunaan");
        setSize(300,294);
        setVisible(true);
        setLayout(new BorderLayout());
        area=new JTextArea();
        area.setRows(16);
        area.setEditable(false);
        
        String isi =""+
                    "1. Untuk melakukan simulasi, pertama tekan input\n"+ 
                    "   dan input datanya lalu sort, setelah selesai\n"+ 
                    "   tekan reset.\n"+
                    "\n"+
                    "2. Untuk input, jika sudah menekan tombol input\n"+ 
                    "   namum tidak jadi, maka cukup dengan mengisi\n"+
                    "   input dengan sembarang angka [1-99] setelah\n"+ 
                    "   itu tekan reset untuk menghapus angka tadi.\n"+ 
                    "   Input data harus berupa angka [1-99] dan tidak\n"+ 
                    "   bisa kosong atau berupa huruf.\n"+
                    "\n"+
                    "3. Untuk sort, jika tombol sort sudah ditekan\n"+ 
                    "   maka simulasi akan berjalan dan tidak dapat\n"+ 
                    "   keluar sampai simulasi selesai. Sort dapat\n"+ 
                    "   dilakukan jika data sudah diinput.\n"+
                    "\n"+
                    "4. Untuk reset, jika tombol reset ditekan maka\n"+ 
                    "   semua data yang sudah diinput akan terhapus.";
        area.setText(isi);
        add(new JScrollPane(area),BorderLayout.NORTH);
    }
}

class QuickSortPanel extends JPanel
{
    private Node node[] = new Node[7];
    private int n;
    private int pointer=0;
    private int kiri;
    private int kanan;
    private int left;
    private int right;
    private String kalimat="";
    private boolean statusTanda;

    public QuickSortPanel()
    {
        statusTanda=false;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        node[6]=new Node(); /*buat posisi right*/
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        setSize(700,230);

        g.setColor(Color.BLACK);
        g.drawString(kalimat,10,200);
        g.setColor(new Color(153,153,255));

        /*gambar pointer high,low,dan mid*/
        if(statusTanda==true)
            {
            tandaBawah(node[left].getPosKotakX()+10,node[left].getPosKotakY()+40,
                    node[left].getPosKotakX()+15,node[left].getPosKotakY()+35,
                    node[left].getPosKotakX()+20,node[left].getPosKotakY()+40,"left",g);
            tandaBawah(node[right].getPosKotakX()+10,node[right].getPosKotakY()+60,
                    node[right].getPosKotakX()+15,node[right].getPosKotakY()+55,
                    node[right].getPosKotakX()+20,node[right].getPosKotakY()+60,"right",g);

            tandaAtas(node[kiri].getPosKotakX()+10,node[kiri].getPosKotakY()-10,
                    node[kiri].getPosKotakX()+15,node[kiri].getPosKotakY()-5,
                    node[kiri].getPosKotakX()+20,node[kiri].getPosKotakY()-10,"kiri",g);
            tandaAtas(node[kanan].getPosKotakX()+10,node[kanan].getPosKotakY()-30,
                    node[kanan].getPosKotakX()+15,node[kanan].getPosKotakY()-25,
                    node[kanan].getPosKotakX()+20,node[kanan].getPosKotakY()-30,"kanan",g);
            }

        for(int a=0;a<n;a++)
        {
            g.setColor(node[a].getWarna());
            g.fillRect(node[a].getPosKotakX(),node[a].getPosKotakY(),30,30);
            g.setColor(Color.BLACK);
            g.drawString(node[a].getAngka()+"",node[a].getPosAngkaX(),node[a].getPosAngkaY());
        }
        
        if(pointer>0)
        {
            showPointer(g);
            g.setColor(Color.BLACK);
            g.drawString("void quickSort(int kr,int kn)",370,10);
            g.drawString("{",370,25);
            g.drawString("  int kiri,kanan,left,right,temp;",370,40);
            g.drawString("  kiri=kr;kanan=kn;left=kiri;right=kanan+1;",370,55);
            g.drawString("  do{",370,70);
            g.drawString("     do{left++;}while(left<kanan && arr[left]<arr[kiri]);",370,85);
            g.drawString("     do{right--;}while(right>kiri && arr[right]>arr[kiri]);",370,100);
            g.drawString("",370,115);
            g.drawString("     if(left<right)",370,130);
            g.drawString("        {temp=arr[left];arr[left]=arr[right];arr[right]=temp;}",370,145);
            g.drawString("  }while(left<right);",370,160);
            g.drawString("  temp=arr[kiri];arr[kiri]=arr[right];arr[right]=temp;",370,175);
            g.drawString("  if(right!=kiri){quickSort(kiri,right-1);}",370,190);  
            g.drawString("  if(right!=kanan){quickSort(right+1,kanan);}",370,205); 
            g.drawString("}",370,220);
        }
    }

    public void tandaAtas(int x1, int y1,int x2, int y2, int x3, int y3, String kata, Graphics g)
    {
        /*bentuk segitiga 1=kiri atas, 2=bawah, 3=kanan atas*/
        int arrayX[]={x1,x2,x3};
        int arrayY[]={y1,y2,y3};
        g.setColor(new Color(153,153,255));
        g.fillPolygon(arrayX,arrayY,3);
        g.setColor(Color.BLUE);
        g.drawString(kata,x1-10,y1-5);
    }

    public void tandaBawah(int x1, int y1,int x2, int y2, int x3, int y3, String kata, Graphics g)
    {
        /*bentuk segitiga 1=kiri bawah, 2=atas, 3=kanan bawah*/
        int arrayX[]={x1,x2,x3};
        int arrayY[]={y1,y2,y3};
        g.setColor(new Color(153,153,255));
        g.fillPolygon(arrayX,arrayY,3);
        g.setColor(Color.BLUE);
        g.drawString(kata,x1-10,y1+10);
    }

    public void showPointer(Graphics g)
    {
        int arrayX[]={355,365,355};
        int arrayY[]={pointer-5,pointer,pointer+5};
        g.setColor(new Color(153,153,255));
        g.fillPolygon(arrayX,arrayY,3);
    }
    
    public void setNode(Node node,int index)
    {
        this.node[index] = node;
    }

    public void setN(int n)
    {
        this.n=n;
    }

    public void setKalimat(String kal)
    {
        this.kalimat=kal;
    }

    public void setStatusTanda(boolean statusTanda)
    {
        this.statusTanda=statusTanda;
    }

    public void setKiri(int kiri)
    {
        this.kiri=kiri;
    }

    public void setKanan(int kanan)
    {
        this.kanan=kanan;
    }

    public void setLeft(int left)
    {
        this.left=left;
    }

    public void setRight(int right)
    {
        this.right=right;
    }

    public void setPointer(int pointer)
    {
        this.pointer=pointer;
    }
    
    public Node getNode(int index)
    {
        return node[index];
    }

    public int getN()
    {
        return n;
    }

    public String getKalimat()
    {
        return kalimat;
    }

    public boolean getStatusTanda()
    {
        return statusTanda;
    }

    public int getKiri()
    {
        return kiri;
    }

    public int getKanan()
    {
        return kanan;
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }
    
    public int getPointer()
    {
        return pointer;
    }
}

class QuickSortFrame extends JFrame
{
    private JButton input = new JButton("Input");
    private JButton sort = new JButton("Sort");
    private JButton reset = new JButton("Reset");
    private JButton help = new JButton("Help");
    private QuickSortPanel panel = new QuickSortPanel();
    private JPanel buttonPanel = new JPanel();
    private BorderLayout tampilan = new BorderLayout();
    private Node node[] = new Node[7];
    private int n;
    private boolean statusInput;
    private boolean statusSort;
    private int counter; /*cek recursive sortnya*/

    /*untuk status true=siap diexecute, false=sedang dijalankan*/

    public QuickSortFrame()
    {
        super("Simulasi Quick Sort");
        statusInput=true;
        statusSort=true;
        counter=0;

        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        node[6]=new Node(); /*buat posisi right*/
        input.setToolTipText("Input data");
        sort.setToolTipText("Sort data");
        reset.setToolTipText("Hapus semua data");
        help.setToolTipText("Petunjuk penggunaan");
        setLayout(tampilan);

        add(panel,BorderLayout.NORTH);

        input.addActionListener(new h());
        sort.addActionListener(new h());
        reset.addActionListener(new h());
        help.addActionListener(new h());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(input);
        buttonPanel.add(sort);
        buttonPanel.add(reset);
        buttonPanel.add(help);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    public void inputData()
    {
        /*validasi banyak angka*/
        do{
            String a=JOptionPane.showInputDialog(null,"Masukkan banyak angka [1-6]","Jumlah Bilangan",JOptionPane.PLAIN_MESSAGE);
            try{
                n=Integer.parseInt(a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);
            }

        if(n>=1 && n<=6)
            {break;}
        }while(true);

        /*validasi input angka*/
        for(int c=1;c<=n;c++)
        {
            int x = 0;
            do{
                String b=JOptionPane.showInputDialog(null,"Masukan angka ke-"+c+" [1-99]","Input",JOptionPane.PLAIN_MESSAGE);
                try
                    {
                    x=Integer.parseInt(b);
                    }
                catch(Exception e)
                    {
                    JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            if(x>=1 && x<=99)
                {break;}
            }while(true);

            node[c-1].setAngka(x);
            node[c-1].setPosAngkaX(20+ 35*(c-1));
            node[c-1].setPosAngkaY(70);
            node[c-1].setPosKotakX(10+ 35*(c-1));
            node[c-1].setPosKotakY(50);
        }

        /*buat node terakhir yang menangani posisi right*/
        node[n].setPosAngkaX(20+ 35*(n));
        node[n].setPosAngkaY(70);
        node[n].setPosKotakX(10+ 35*(n));
        node[n].setPosKotakY(50);
        node[n].setWarna(Color.WHITE);

        /*status jadi false supaya tdk bisa input ulang*/
        statusInput=false;
    }



    public void quickSort(int kr,int kn)
    {
        int kiri,kanan,left,right,temp;
        kiri=kr;
        kanan=kn;
        left=kiri;
        right=kanan+1;

        statusSort=true;
        /*inisialisasi panel*/
        panel.setN(n);
        panel.setKiri(kiri);
        panel.setKanan(kanan);
        panel.setLeft(left);
        panel.setRight(right);
        panel.setKalimat("");
        panel.setPointer(35);

        for(int a=0;a<=n;a++)
        {
            panel.setNode(node[a],a);
        }
        panel.repaint();repaint();
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);

        panel.setStatusTanda(true);
        panel.repaint();repaint();
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);

        do{
            do{
                left++;

                panel.setLeft(left);
                panel.setKalimat("Jika left < kiri, maka left bergeser kekanan");
                panel.setPointer(80);
                panel.repaint();repaint();
                /*pemberitahuan*/
                JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);

            }while(left<kanan && node[left].getAngka()<node[kiri].getAngka());

            do{
                right--;

                panel.setRight(right);
                panel.setKalimat("Jika right > kiri, maka right bergeser kekiri");
                panel.setPointer(95);
                panel.repaint();repaint();
                /*pemberitahuan*/
                JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);

            }while(right>kiri && node[right].getAngka()>node[kiri].getAngka());

            panel.setKalimat("Jika posisi left < posisi right, maka ditukar");
            panel.setPointer(125);
            panel.repaint();repaint();
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);

            if(left<right)
            {
                temp=node[left].getAngka();
                node[left].setAngka(node[right].getAngka());
                node[right].setAngka(temp);
            }
        }while(left<right);

        panel.setKalimat("Tukar kiri dengan right");
        node[right].setWarna(new Color(0,255,51));
        panel.setNode(node[right],right);
        panel.setPointer(175);
        panel.repaint();repaint();
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);

        temp=node[kiri].getAngka();
        node[kiri].setAngka(node[right].getAngka());
        node[right].setAngka(temp);

        if(right!=kiri)
        {
            counter++;
            quickSort(kiri,right-1);
            counter--;
        }

        if(right!=kanan)
        {
            counter++;
            quickSort(right+1,kanan);
            counter--;
        }

        if(counter==0)
        {
        int a;
        panel.setKalimat("Quick sort selesai");
        panel.setPointer(215);
        panel.setStatusTanda(false);
        for(a=0;a<n;a++)
            {
            node[a].setWarna(Color.YELLOW);
            panel.setNode(node[a],a);
            }
        panel.repaint();repaint();
        }

        statusSort=false;
    }

    public void hapusData()
    {
        statusInput=true;
        statusSort=true;
        panel.setStatusTanda(false);
        n=0;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        panel.setN(n);
        panel.setBackground(Color.WHITE);
        panel.setKalimat("");
        panel.setPointer(0);
        panel.repaint();
        repaint();
    }

    public void tampilPetunjuk()
    {
        Petunjuk show=new Petunjuk();
    }
    
    private class h implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==input)
            {
                if(statusInput==true)
                    {inputData();}
                else
                    {JOptionPane.showMessageDialog(null,"Sudah diinput!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }

            if(e.getSource()==sort)
            {
                if(statusSort==true)
                    {
                    if(statusInput==false)
                        {
                        quickSort(0,n-1);
                        }
                    else
                        {JOptionPane.showMessageDialog(null,"Belum input data!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
                    }
                else
                    {JOptionPane.showMessageDialog(null,"Sedang disort!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }

            if(e.getSource()==reset)
            {
                hapusData();
            }

            if(e.getSource()==help)
            {
                tampilPetunjuk();
            }
        }
    }
}

public class QuickSortSimulation
{
    public static void main(String args[])
    {
        QuickSortFrame quick = new QuickSortFrame();
        quick.setSize(700,300);
        quick.setVisible(true);
        quick.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

