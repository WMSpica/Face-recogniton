package Recognizer;

import ConData.ConnectionDB;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public final class FaceRecognizer extends javax.swing.JFrame {

    private FaceRecognizer.DaemonThread myThread = null;
    
        VideoCapture webSource = null;
        Mat camImage = new Mat();   
            CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");  
            LBPHFaceRecognizer faceRec = LBPHFaceRecognizer.create();    
        BytePointer mem = new BytePointer();
        RectVector detectedFace = new RectVector();
    
    String root, imeOsobe, prezimeOsobe,
            godinaRodjenjaOsobe, mailOsobe;
    int idOsoba;
    ConnectionDB conect = new ConnectionDB();
    
    public FaceRecognizer() {
        initComponents();
        faceRec.read("C:\\photos\\ClassifilerLBPH.yml");
        faceRec.setThreshold(90);
        pokreniKameruR();
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ime_osobe = new javax.swing.JLabel();
        dodatno_inf = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        face_recognizer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Face recognizer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(134, 226, 213));

        jLabel1.setBackground(new java.awt.Color(134, 226, 213));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gledajte u kameru ");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, 70));

        jPanel2.setBackground(new java.awt.Color(134, 226, 213));

        ime_osobe.setBackground(new java.awt.Color(255, 255, 255));
        ime_osobe.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        ime_osobe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ime_osobe.setText("Ime i prezime");
        ime_osobe.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        ime_osobe.setOpaque(true);

        dodatno_inf.setBackground(new java.awt.Color(255, 255, 255));
        dodatno_inf.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        dodatno_inf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dodatno_inf.setText("Mail adresa");
        dodatno_inf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        dodatno_inf.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ime_osobe, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(dodatno_inf, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dodatno_inf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ime_osobe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 380, 70));

        face_recognizer.setBackground(new java.awt.Color(255, 255, 255));
        face_recognizer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        face_recognizer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        face_recognizer.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(face_recognizer, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(face_recognizer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 380, 380));

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
            java.util.logging.Logger.getLogger(FaceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new FaceRecognizer().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dodatno_inf;
    private javax.swing.JLabel face_recognizer;
    private javax.swing.JLabel ime_osobe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

class DaemonThread implements Runnable {

        protected boolean runnable = false;
        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            
                            webSource.retrieve(camImage);
                            Graphics g = face_recognizer.getGraphics();

                                Mat imageGray = new Mat();
                                cvtColor(camImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                
                                    Rect face = detectedFace.get(i);
                                    rectangle(camImage, face, new Scalar(217, 30, 24, 5), 2, 0, 0);
                                    Mat faceCap = new Mat(imageGray, face);
                                    opencv_imgproc.resize(faceCap, faceCap, new Size(150, 150));

                                IntPointer iPoint = new IntPointer(1);
                                DoublePointer conf = new DoublePointer(1);
                                faceRec.predict(faceCap, iPoint, conf);
                                int prediction = iPoint.get(0);
                                if (prediction == -1) {
                                    rectangle(camImage, face, new Scalar(0, 0, 255, 3), 2, 0, 0);
                                    ime_osobe.setText("Nepoznata osoba!");
                                    dodatno_inf.setText("Nepoznato!");
                                    idOsoba = 0;
                                    System.out.println("Nepoznata osoba!"); 
                                } else {
                                    rectangle(camImage, face, new Scalar(217, 30, 24, 5), 2, 0, 0);
                                    System.out.println(conf.get(0));
                                    idOsoba = prediction;
                                    rec();
                                }
                            }
                                    imencode(".bmp", camImage, mem);
                                    Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                                    BufferedImage buff = (BufferedImage) im;

                            if (g.drawImage(buff, 0, 0, 270, 360, null)) {
                                if (runnable == false) {
                                    this.wait();
                                }
                            }
                        }
                      } catch (IOException | InterruptedException ex) {
                    }
                }
            }
        }
}
        private void rec() {
            SwingWorker worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    conect.conn();   
                    try{
                            conect.executSQL("SELECT * FROM osoba WHERE id = " + String.valueOf(idOsoba));
                            while(conect.rs.next()){
                                ime_osobe.setText(conect.rs.getString("ime") + "  " + conect.rs.getString("prezime"));
                                dodatno_inf.setText(conect.rs.getString("mail"));
                                System.out.println("Osoba sa ID: " + conect.rs.getString("id"));
                            }
                        }catch(Exception e){}
                    conect.cloconn();
                    return null;
                }
            };
        worker.execute();
    }
    public void stopKameraR(){
        myThread.runnable = false;
        webSource.release();
        dispose();
    }    
    public void pokreniKameruR(){
        webSource = new VideoCapture(0);
        myThread = new FaceRecognizer.DaemonThread();
        Thread fr = new Thread(myThread);
        fr.setDaemon(true);
        myThread.runnable = true;
        fr.start();
            
    }               
}