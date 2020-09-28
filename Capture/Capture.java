package Capture;

import ConData.ConnectionDB;
import ConData.ControlOsoba;
import ConData.ModelOsoba;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class Capture extends javax.swing.JFrame {
    
    private Capture.DaemonThread myThread = null;
    
    VideoCapture webSource = null;
    Mat camImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    RectVector detectedFace = new RectVector();
    
    String root, imeOsobe, prezimeOsobe, godinaRodjenjaOsobe, mailOsobe;
    
    int brojSlika = 50;
    int Slika = 1;
    int idOsoba;
    
    ConnectionDB conect = new ConnectionDB();
    ControlOsoba con;
    ModelOsoba mod;

    public Capture(int id, String iOsoba, String pOsoba, String grOsoba, String mOsoba) {
        
        initComponents();
        idOsoba = id;
        imeOsobe = iOsoba;
        prezimeOsobe = pOsoba;
        godinaRodjenjaOsobe = grOsoba;
        mailOsobe = mOsoba;
        pokreniKameru();
    }

    private Capture() {
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_photo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        broj_Slika = new javax.swing.JLabel();
        save_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Capture snapshots from users");
        setBackground(new java.awt.Color(200, 247, 197));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_photo.setBackground(new java.awt.Color(255, 255, 255));
        label_photo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));

        javax.swing.GroupLayout label_photoLayout = new javax.swing.GroupLayout(label_photo);
        label_photo.setLayout(label_photoLayout);
        label_photoLayout.setHorizontalGroup(
            label_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        label_photoLayout.setVerticalGroup(
            label_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        getContentPane().add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 270, 360));

        jPanel2.setBackground(new java.awt.Color(134, 226, 213));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Capture snapshots");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, 70));

        jPanel3.setBackground(new java.awt.Color(134, 226, 213));

        broj_Slika.setBackground(new java.awt.Color(255, 255, 255));
        broj_Slika.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        broj_Slika.setForeground(new java.awt.Color(102, 102, 102));
        broj_Slika.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        broj_Slika.setText("00");
        broj_Slika.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        broj_Slika.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        broj_Slika.setOpaque(true);

        save_button.setBackground(new java.awt.Color(38, 166, 91));
        save_button.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        save_button.setForeground(new java.awt.Color(255, 255, 255));
        save_button.setText("Capture");
        save_button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        save_button.setContentAreaFilled(false);
        save_button.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(save_button, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(broj_Slika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(110, 110, 110))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(broj_Slika, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 380, 70));

        setSize(new java.awt.Dimension(396, 579));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new Capture().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel broj_Slika;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel label_photo;
    private javax.swing.JButton save_button;
    // End of variables declaration//GEN-END:variables

  class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(camImage);
                            Graphics g = label_photo.getGraphics();
                            Mat imageColor = new Mat();
                            imageColor = camImage;

                            Mat imageGray = new Mat();
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector();
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) {
                                Rect dadosFace = detectedFaces.get(0);
                                rectangle(imageColor, dadosFace, new Scalar(47, 179, 43, 5));
                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(150, 150));

                                if (save_button.getModel().isPressed()) {
                                   
                                    if (Slika <= brojSlika) {
                                        String cropped = "C:\\photos\\osoba." + idOsoba + "." + Slika + ".jpg";
                                        imwrite(cropped, face);
                                        broj_Slika.setText(String.valueOf(Slika));
                                        Slika++;
                                    }
                                    if (Slika > 50) {
                                        upisiDB();
                                        generisiSlike();
                                        System.out.println("Fajl generisan!");
                                        stopirajKameru();
                                    }
                                }
                            }

                                imencode(".bmp", camImage, mem);
                                Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                                BufferedImage buff = (BufferedImage) im;

                            if (g.drawImage(buff, 0, 0, 270, 360, null)) {
                                if (runnable == false) {
                                    //System.out.println("Sacuvaj sliku");
                                    this.wait();
                                }
                            }
                        }
                    } catch (IOException | InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Greska kod pokretanja kamere!!!" + ex);
                    }
                }
            }
        }
    }
       
    private void generisiSlike() {
        
            File directory = new File("C:\\photos\\");
            FilenameFilter filter = (File dir, String name1) -> name1.endsWith(".jpg");
               
                File [] files = directory.listFiles(filter);
                MatVector photos = new MatVector(files.length);
                Mat lab = new Mat(files.length, 1, CV_32SC1);  
                IntBuffer Buff = lab.createBuffer();
            
            int brojac = 0;
            for(File image : files){
                   
                Mat img = imread(image.getAbsolutePath(), IMREAD_GRAYSCALE); 
                int idOs = Integer.parseInt(image.getName().split("\\.")[1]);
                opencv_imgproc.resize(img, img, new Size(150, 150));
            
             photos.put(brojac, img);
             Buff.put(brojac, idOs);
             brojac++;
            }
             
            FaceRecognizer lbph = LBPHFaceRecognizer.create();
            lbph.train(photos, lab);
            lbph.save("C:\\photos\\ClassifilerLBPH.yml");
    }

        private void stopirajKameru() {
            myThread.runnable = false;
            webSource.release();
            dispose();
            
        }
        private void upisiDB() {
           
             con = new ControlOsoba();
             mod = new ModelOsoba();
          
             mod.setId(idOsoba);
             mod.setIme(imeOsobe);
             mod.setPrezime(prezimeOsobe);
             mod.setGodina_rodjenja(godinaRodjenjaOsobe);
             mod.setMail(mailOsobe);
             
             con.insert(mod);
            
        }
         private void pokreniKameru() {
             
             webSource = new VideoCapture(0);
             myThread = new Capture.DaemonThread();
             Thread t = new Thread(myThread);
             t.setDaemon(true);
             myThread.runnable = true;
             t.start();    
             
    }          
}