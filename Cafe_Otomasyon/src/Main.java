import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame {

    private JTextField menu_IDField, Menu_AdField, FiyatField;
    private JTextArea urunListesiArea;

    class Baglanti {
        private String kullanici = "root";
        private String sifre = "";
        private Connection connection = null;

        public Baglanti() {
            //sonuncu kısım türkçe karakter için
            String url = "jdbc:mysql://localhost:3306/cafe" + "?useUnicode=true&characterEncoding=utf8";
            try {
                Class.forName("com.mysql.jdbc.Driver:");//jdbc'yi çağırdık
            } catch (Exception e) {
                System.out.println("driver yok");
            }

            try {
                connection = DriverManager.getConnection(url, kullanici, sifre);
                System.out.println("bağlantı başarılı");
            } catch (SQLException e) {
                System.out.println("başarısız");
                e.printStackTrace();//hatanın nerede olduğunu söyler
            }
        }
    }


    public Main() {

        setTitle("Ürün İşlemleri Formu");

        // Text alanları ve etiketleri
        menu_IDField = new JTextField(5);
        Menu_AdField = new JTextField(30);
        FiyatField = new JTextField(10);

        JLabel menu_IDLabel = new JLabel("ID:");
        JLabel Menu_AdLabel = new JLabel("Menü Adı:");
        JLabel FiyatLabel = new JLabel("Fiyat:");

        // Listeleme alanı
        urunListesiArea = new JTextArea(15, 40);
        urunListesiArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(urunListesiArea);

        // Butonlar
        JButton listeleButton = new JButton("Ürünleri Listele");
        JButton ekleButton = new JButton("Ürün Ekle");
        JButton guncelleButton = new JButton("Ürün Güncelle");
        JButton silButton = new JButton("Ürün Sil");

        // Butonlara action listener ekleme
        listeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urunleriListele();
            }
        });

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urunEkle();
            }
        });

        guncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urunGuncelle();
            }
        });

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urunSil();
            }
        });

        // Panel oluşturma ve bileşenleri eklem
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(menu_IDLabel);
        panel.add(menu_IDField);
        panel.add(Menu_AdLabel);
        panel.add(Menu_AdField);
        panel.add(FiyatLabel);
        panel.add(FiyatField);
        panel.add(listeleButton);
        panel.add(ekleButton);
        panel.add(guncelleButton);
        panel.add(silButton);

        // Ana konteyner'a bileşenleri ekleme
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.WEST);
        container.add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        giris(); // Giriş metodu çağrıldı

    }


    private void giris() {
        JTextField P_IDField = new JTextField();
        JPasswordField sifreField = new JPasswordField();

        Object[] mesaj = {
                "Personel ID:", P_IDField,
                "Şifre:", sifreField
        };

        int secenek = JOptionPane.showConfirmDialog(null, mesaj, "Giriş", JOptionPane.OK_CANCEL_OPTION);

        if (secenek == JOptionPane.OK_OPTION) {
            int P_ID = Integer.parseInt(P_IDField.getText());
            char[] sifreChars = sifreField.getPassword();
            String sifre = new String(sifreChars);

            if (PersonelGiris(P_ID, sifre) != 0) {
                System.out.println("Giriş başarılı.");
            } else {
                JOptionPane.showMessageDialog(null, "Giriş başarısız. Sistem kapatılıyor.");
                System.out.println("Yetki dışı giriş veya Hatalı giriş!");
                System.exit(0); // Sistemi kapat
            }
        } else {
            System.out.println("Çıkış yapıldı.");
            System.exit(0); // Sistemi kapat
        }
        System.out.println("Sistem kapatıldı.");
    }

    private int PersonelGiris(int P_ID, String sifre) {
        try {
            Baglanti baglanti = new Baglanti();
            Connection connection = baglanti.connection;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM giris WHERE P_ID=? AND sifre=?");

            preparedStatement.setInt(1, P_ID);
            preparedStatement.setString(2, sifre);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultSet.close();
                return 1; // Kullanıcı bulundu, başarılı giriş
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Kullanıcı bulunamadı veya veritabanı hatası
    }

        //Veritabanındaki ürünleri listeleme
        private void urunleriListele() {
            try {
                Baglanti baglanti = new Baglanti();
                Connection connection = baglanti.connection;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM menu");

                StringBuilder sb = new StringBuilder();
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("menu_ID") + resultSet.getString("Menu_Ad") + resultSet.getDouble("Fiyat"));
                    int menu_ID = resultSet.getInt("menu_ID");
                    String Menu_Ad = resultSet.getString("Menu_Ad");
                    double Fiyat = resultSet.getDouble("Fiyat");

                    sb.append("menu_ID: ").append(menu_ID).append(", Menu_Ad: ").append(Menu_Ad).append(", Fiyat: ").append(Fiyat).append("\n");
                }

                urunListesiArea.setText(sb.toString());

                resultSet.close();
                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Yeni ürün ekleme
        private void urunEkle() {
            try {
                Baglanti baglanti = new Baglanti();
                Connection connection = baglanti.connection;
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO menu (menu_ID,Menu_Ad, Fiyat) VALUES (?, ?, ?)");

                int menu_ID = Integer.parseInt(menu_IDField.getText());
                String Menu_Ad = Menu_AdField.getText();
                double Fiyat = Double.parseDouble(FiyatField.getText());

                preparedStatement.setInt(1, menu_ID);
                preparedStatement.setString(2, Menu_Ad);
                preparedStatement.setDouble(3, Fiyat);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();

                urunleriListele(); // Güncellenmiş listeyi göster

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Ürün güncelleme
        private void urunGuncelle() {
            try {
                Baglanti baglanti = new Baglanti();
                Connection connection = baglanti.connection;
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE menu SET Menu_Ad=?, Fiyat=? WHERE menu_id=?");

                int menu_ID = Integer.parseInt(menu_IDField.getText());
                String Menu_Ad = Menu_AdField.getText();
                double Fiyat = Double.parseDouble(FiyatField.getText());

                preparedStatement.setString(1, Menu_Ad);
                preparedStatement.setDouble(2, Fiyat);
                preparedStatement.setInt(3, menu_ID);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();

                urunleriListele(); // Güncellenmiş listeyi göster

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Ürün silme
        private void urunSil() {
            try {
                Baglanti baglanti = new Baglanti();
                Connection connection = baglanti.connection;
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM menu WHERE menu_ID=?");

                int menu_ID = Integer.parseInt(menu_IDField.getText());

                preparedStatement.setInt(1, menu_ID);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();

                urunleriListele(); // Güncellenmiş listeyi göster

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Main form = new Main();
                form.setVisible(true);
            });
        }

    }
