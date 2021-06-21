
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.WindowEvent;
import java.sql.*;

public class CGrageda_MMV6 {
    private JFrame frame;
    private JPanel panel;
    private JLabel logo = new JLabel("Master Mind");
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private Font default_font = new Font("Verdana",Font.PLAIN,16);
    public CGrageda_MMV6(){
        //General
        frame = new JFrame("Master Mind");
        frame.setSize(600,700);
        panel = (JPanel)frame.getContentPane();
        panel.setLayout(new GridBagLayout());
        logo.setFont(new Font("Verdana",Font.BOLD,50));
        //Inicializacion de Componentes
        b1 = new JButton("Jugar");
        b2 = new JButton("Instrucciones");
        b3 = new JButton("Cargar Partida");
        b4 = new JButton("Salir");

        //Panel de botones
        JPanel panel_Botones = new JPanel();
        panel_Botones.setLayout(new GridBagLayout());
        b1.setFont(default_font);
        b2.setFont(default_font);
        b3.setFont(default_font);
        b4.setFont(default_font);
        panel_Botones.add(b1,new GridBagConstraints(0,0,2,1,1.0,0.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(10,0,0,0),75,10));
        panel_Botones.add(b2,new GridBagConstraints(0,1,2,1,1.0,0.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(10,0,0,0),10,10));
        panel_Botones.add(b3,new GridBagConstraints(0,2,2,1,1.0,0.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(10,0,0,0),10,10));
        panel_Botones.add(b4,new GridBagConstraints(0,3,2,1,1.0,0.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(10,0,0,0),85,10));

        //Añadir componentes al panel principal
        panel.add(logo,new GridBagConstraints(0,0,2,1,1.0,1.0,GridBagConstraints.NORTH,GridBagConstraints.CENTER,new Insets(0,0,0,0),0,0));
        panel.add(panel_Botones,new GridBagConstraints(0,1,2,1,1.0,1.0,GridBagConstraints.NORTH,GridBagConstraints.CENTER,new Insets(0,0,0,0),0,0));

        //Funcionalidad
        b1.addActionListener(new B1ActListener());
        b2.addActionListener(new B2ActListener());
        b3.addActionListener(new B3ActListener());
        b4.addActionListener(new B4ActListener());
        //Visivilidad Frame
        frame.addWindowListener(new BWinListener());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private class B4ActListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    private class B3ActListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new Pantalla_Cargar_Partida();
            frame.dispose();
        }
    }
    private class B2ActListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new Pantalla_Instrucciones();
            frame.dispose();
        }
    }
    private class B1ActListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new Pantalla_Juego(new Partida_BD());
            frame.setVisible(false);
        }
    }
    private class BWinListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        CGrageda_MMV6 obj = new CGrageda_MMV6();

    }
}
class Pantalla_Instrucciones {
    private JFrame frame;
    private JPanel panel;
    private JMenuItem inicio;
    private JMenuItem salir;
    private JLabel logo = new JLabel("Master Mind");
    private Font default_font = new Font("Verdana",Font.PLAIN,13);
    private JTextArea informacion_Instrucciones;
    public Pantalla_Instrucciones(){
        frame = new JFrame("Master Mind");
        frame.setSize(600,700);
        panel = (JPanel)frame.getContentPane();
        panel.setLayout(new GridBagLayout());
        logo.setFont(new Font("Verdana",Font.BOLD,50));

        //Menu
        JMenuBar barra_Menu = new JMenuBar();
        inicio = new JMenuItem("Inicio");
        salir = new JMenuItem("Salir");
        barra_Menu.add(inicio,new GridBagConstraints(0,0,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        barra_Menu.add(salir,new GridBagConstraints(1,0,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //Dimensionar Area de texto
        informacion_Instrucciones = new JTextArea();
        informacion_Instrucciones.setPreferredSize(new Dimension(550,300));
        informacion_Instrucciones.setText(Juego.INSTRUCCIONES);
        informacion_Instrucciones.setEditable(false);


        //Añadiendo Componentes Panel Principal
        panel.add(logo,new GridBagConstraints(0,1,3,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        panel.add(informacion_Instrucciones,new GridBagConstraints(0,2,3,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //Funcionalidad
        inicio.addActionListener(new Pantalla_Instrucciones.Go_Home());
        salir.addActionListener(new Pantalla_Instrucciones.Go_Out());

        //Visivilidad Frame
        frame.setJMenuBar(barra_Menu);
        frame.addWindowListener(new Pantalla_Instrucciones.BWinListener());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private class Go_Home implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new CGrageda_MMV6();
            frame.setVisible(false);
        }
    }
    private class Go_Out implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
    private class BWinListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
class Pantalla_Juego {
    private JFrame frame;
    private JPanel panel;
    private JMenuItem inicio;
    private JMenuItem salir;
    private JLabel logo = new JLabel("Master Mind");
    private Font default_font = new Font("Verdana", Font.PLAIN, 13);
    private JButton b1;
    private JTextArea informacion;
    private JTextField entrada_Usuario;
    private JCheckBox modo_dificil;
    private JLabel l1;
    private static int numTiradas = 0;
    private Partida_BD partida_bd;

    public Pantalla_Juego(Partida_BD partida_bd) {
        //Aspectos Generales
        this.partida_bd = partida_bd;
        frame = new JFrame("Master Mind");
        frame.setSize(700, 700);
        panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridBagLayout());
        logo.setFont(new Font("Verdana", Font.BOLD, 50));



        //Inicializacion de componentes
        informacion = new JTextArea("Realiza un tirada");
        informacion.setFont(default_font);

        l1 = new JLabel("Numero : ");
        l1.setFont(default_font);
        entrada_Usuario = new JTextField("", 10);
        entrada_Usuario.setFont(default_font);
        modo_dificil = new JCheckBox("Dificil?");
        modo_dificil.setFont(default_font);
        b1 = new JButton("Introducir numero");
        b1.setFont(default_font);

        //Panel Usuario
        JPanel user_Panel = new JPanel();
        user_Panel.setLayout(new GridBagLayout());
        user_Panel.add(l1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        user_Panel.add(entrada_Usuario, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 100), 0, 0));
        user_Panel.add(modo_dificil, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, -100, 0, 0), 0, 0));

        //Dimensionar Area de texto
        informacion.setAlignmentX(SwingConstants.CENTER);
        informacion.setPreferredSize(new Dimension(355, 500));
        informacion.setEditable(false);

        //Menu
        JMenuBar barra_Menu = new JMenuBar();
        inicio = new JMenuItem("Inicio");
        salir = new JMenuItem("Salir");
        barra_Menu.add(inicio, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        barra_Menu.add(salir, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        //Añadiendo componentes al panel Principal
        panel.add(logo, new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(informacion, new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(user_Panel, new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(b1, new GridBagConstraints(0, 4, 2, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 10));


        //Funcionalidad
        b1.addActionListener(new Pantalla_Juego.B1ActListener());
        inicio.addActionListener(new Pantalla_Juego.Go_Home());
        salir.addActionListener(new Pantalla_Juego.Go_Out());

        //Visivilidad Frame
        frame.setJMenuBar(barra_Menu);
        frame.addWindowListener(new Pantalla_Juego.BWinListener());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //MUESTRA INFORMACION DE PARTIDAS ANTERIORES
        informacion.setText(" Usuario\t" + "ayuda\t" + "Correctos\tincorrectos\n" + partida_bd.consultar_Tiradas(partida_bd.idPartida));

    }

    private class Go_Home implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new CGrageda_MMV6();
            frame.setVisible(false);
        }
    }

    private class Go_Out implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
    private class B1ActListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            numTiradas = partida_bd.getNumTirada();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            if(numTiradas <= 9 && Juego.control(entrada_Usuario.getText()) ){

                partida_bd.afeguirTirada(entrada_Usuario.getText());
                sb.append(partida_bd.toString(true));
                sb1.append(partida_bd.toString());

                if (modo_dificil.isSelected())informacion.setText(" Usuario\tCorrectos\tincorrectos\n"+sb.toString());
                else informacion.setText(" Usuario\tCorrectos\tincorrectos\tayuda\n"+sb1.toString());

                if (partida_bd.comprobarTirada(entrada_Usuario.getText())){
                    partida_bd.update_Terminado(true);
                    informacion.append("\t     GANASTE!!!!");
                    informacion.setFont(new Font("Comic Sans MS",Font.PLAIN,13));
                    entrada_Usuario.setEditable(false);
                    b1.setBackground(Color.GREEN);
                    b1.setEnabled(false);
                    numTiradas = 0;
                }
                numTiradas++;
            }else if (numTiradas > 9){
                partida_bd.update_Terminado(true);
                informacion.append("\n\t     PERDISTE!!!!");
                informacion.setFont(new Font("Comic Sans MS",Font.PLAIN,13));
                b1.setBackground(Color.RED);
                b1.setEnabled(false);
                numTiradas = 0;

            } else informacion.setText("Introduce un numero de 5 cifras entre [0-9]");
        }
    }

    private class BWinListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
class Pantalla_Cargar_Partida {
    private JFrame frame;
    private JPanel panel;
    private JMenuItem inicio;
    private JMenuItem salir;
    private JLabel logo = new JLabel("Master Mind");
    private Font default_font = new Font("Verdana",Font.PLAIN,13);
    private JTextArea informacion_Ultima_Partida;
    private JLabel seleccionar;
    private JTextField id_Partida;
    private JButton cargar = new JButton("Cargar Partida");
    public Pantalla_Cargar_Partida(){
        frame = new JFrame("Master Mind");
        frame.setSize(600,700);
        panel = (JPanel)frame.getContentPane();
        panel.setLayout(new GridBagLayout());
        logo.setFont(new Font("Verdana",Font.BOLD,50));
        //Menu
        JMenuBar barra_Menu = new JMenuBar();
        inicio = new JMenuItem("Inicio");
        salir = new JMenuItem("Salir");
        barra_Menu.add(inicio,new GridBagConstraints(0,0,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        barra_Menu.add(salir,new GridBagConstraints(1,0,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //Dimensionar Area de texto

        informacion_Ultima_Partida = new JTextArea();
        informacion_Ultima_Partida.setPreferredSize(new Dimension(370,500));
        informacion_Ultima_Partida.setEditable(false);
        informacion_Ultima_Partida.setText("\tID_PARTIDA\tESTADO\n");
        informacion_Ultima_Partida.append(Partida_BD.consultar_Partida());


        //PANEL SELECCIONAR PARTIDA
        JPanel select_Partida = new JPanel();
        seleccionar = new JLabel("Ingrese ID PARTIDA : ");
        id_Partida = new JTextField("",10);
        select_Partida.setLayout(new GridBagLayout());
        select_Partida.add(seleccionar,new GridBagConstraints(0,0,1,1,0.0,0.0,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,new Insets(10,5,0,0),0,0));
        select_Partida.add(id_Partida,new GridBagConstraints(1,0,1,1,0.0,0.0,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,new Insets(10,5,0,0),0,0));
        select_Partida.add(cargar,new GridBagConstraints(0,1,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(10,5,5,5),0,0));


        //Añadiendo Componentes Panel Principal
        panel.add(logo,new GridBagConstraints(0,1,3,1,1.0,1.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        panel.add(informacion_Ultima_Partida,new GridBagConstraints(0,2,3,1,1.0,1.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        panel.add(select_Partida,new GridBagConstraints(1,3,2,1,0.0,0.0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //Funcionalidad
        inicio.addActionListener(new Pantalla_Cargar_Partida.Go_Home());
        salir.addActionListener(new Pantalla_Cargar_Partida.Go_Out());
        cargar.addActionListener(new Load_Game());


        //Visivilidad Frame
        frame.setJMenuBar(barra_Menu);
        frame.addWindowListener(new Pantalla_Cargar_Partida.BWinListener());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private class Load_Game implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            if (!id_Partida.getText().equals("")) {
                Partida_BD bd;
                int id = 0;
                try {
                    id = Integer.parseInt(id_Partida.getText());
                } catch (NumberFormatException e) { }
                bd = new Partida_BD(id);
                if (!bd.getTerminado()){
                    new Pantalla_Juego(bd);
                }else{
                    new Pantalla_Finalized_Game(bd);
                }
                frame.dispose();

            }
            informacion_Ultima_Partida.append("\t    SELCCIONA UNA PARTIDA");
        }
    }
    private class Go_Home implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new CGrageda_MMV6();
            frame.setVisible(false);
        }
    }
    private class Go_Out implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
    private class BWinListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
class Pantalla_Finalized_Game{
    private JFrame frame;
    private JPanel panel;
    private JMenuItem inicio;
    private JMenuItem salir;
    private JLabel logo = new JLabel("Master Mind");
    private Font default_font = new Font("Verdana", Font.PLAIN, 13);
    private JTextArea informacion;
    private Partida_BD partida_bd;

    public Pantalla_Finalized_Game(Partida_BD partida_bd) {
        //Aspectos Generales
        this.partida_bd = partida_bd;
        frame = new JFrame("Master Mind");
        frame.setSize(700, 700);
        panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridBagLayout());
        logo.setFont(new Font("Verdana", Font.BOLD, 50));


        //Inicializacion de componentes
        informacion = new JTextArea("Realiza un tirada");
        informacion.setFont(default_font);



        //Panel Usuario
        JPanel user_Panel = new JPanel();
        user_Panel.setLayout(new GridBagLayout());
        //Dimensionar Area de texto
        informacion.setAlignmentX(SwingConstants.CENTER);
        informacion.setPreferredSize(new Dimension(355, 500));
        informacion.setEditable(false);

        //Menu
        JMenuBar barra_Menu = new JMenuBar();
        inicio = new JMenuItem("Inicio");
        salir = new JMenuItem("Salir");
        barra_Menu.add(inicio, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        barra_Menu.add(salir, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        //Añadiendo componentes al panel Principal
        panel.add(logo, new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(informacion, new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(user_Panel, new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));


        //Funcionalidad
        inicio.addActionListener(new Go_Home());
        salir.addActionListener(new Go_Out());

        //Visivilidad Frame
        frame.setJMenuBar(barra_Menu);
        frame.addWindowListener(new BWinListener());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //MUESTRA INFORMACION DE PARTIDAS ANTERIORES

        informacion.setText(" Usuario\t" + "ayuda\t" + "Correctos\tincorrectos\n" + partida_bd.consultar_Tiradas(partida_bd.idPartida));
        informacion.append("\t    PARTIDA TERMINADA");

    }
    private class Go_Home implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new CGrageda_MMV6();
            frame.setVisible(false);
        }
    }

    private class Go_Out implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    private class BWinListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
class Juego {
    private static final String LOGO = "\n" +
            "\n" +
            "                         /$$      /$$  /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$$$ /$$$$$$$              /$$      /$$ /$$$$$$ /$$   /$$ /$$$$$$$       \n" +
            "                        | $$$    /$$$ /$$__  $$ /$$__  $$|__  $$__/| $$_____/| $$__  $$            | $$$    /$$$|_  $$_/| $$$ | $$| $$__  $$      \n" +
            "                        | $$$$  /$$$$| $$  \\ $$| $$  \\__/   | $$   | $$      | $$  \\ $$            | $$$$  /$$$$  | $$  | $$$$| $$| $$  \\ $$      \n" +
            "                        | $$ $$/$$ $$| $$$$$$$$|  $$$$$$    | $$   | $$$$$   | $$$$$$$/            | $$ $$/$$ $$  | $$  | $$ $$ $$| $$  | $$      \n" +
            "                        | $$  $$$| $$| $$__  $$ \\____  $$   | $$   | $$__/   | $$__  $$            | $$  $$$| $$  | $$  | $$  $$$$| $$  | $$      \n" +
            "                        | $$\\  $ | $$| $$  | $$ /$$  \\ $$   | $$   | $$      | $$  \\ $$            | $$\\  $ | $$  | $$  | $$\\  $$$| $$  | $$      \n" +
            "                        | $$ \\/  | $$| $$  | $$|  $$$$$$/   | $$   | $$$$$$$$| $$  | $$            | $$ \\/  | $$ /$$$$$$| $$ \\  $$| $$$$$$$/      \n" +
            "                        |__/     |__/|__/  |__/ \\______/    |__/   |________/|__/  |__/            |__/     |__/|______/|__/  \\__/|_______/       \n" +
            "                                                                                                                                                  \n" +
            "                                                                                                                                                  \n" +
            "                                                                                                                                                  \n" +
            "\n";
    protected static final String INSTRUCCIONES = "Bienvenido al Juego master mind \n"+
            "Instrucciones : \n" +
            "1. Se generan un numero de 5 cifras aleatorio, el objetivo es adivinar dicho numero. \n" +
            "2. Para ayudar ha encontrar los numeros se proporcionara una ayuda : \n" +
            "\ta. Se muestra 0 si el numero no es correcto. \n" +
            "\tb. Se muestra 1 si el numero es correcto y esta bien posicionado. \n" +
            "\tc. Se muestra 2 si el numero es correcto pero no esta bien posicionado. \n" +
            "3. Tiene 10 intentos para adivinar el numero.\n" +
            "4. Puede elejir entre dos dificultades Facil o Dificil (Dificultad dificil no consta de ayuda.) \n"+
            "Nota : Los numeros se pueden repetir.";
    /*METODO PARA LIMPIAR PANTALLA*/
    private static final void limpiarPantalla(){
        try{
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }catch(Exception e){
            System.out.println("Error de programa");
        }
    }
    /*METODO CONTROL DE ENTRADA DE USUARIO*/
    public static final boolean control(String entrada){
        if(entrada.length() != 5)return false;
        try{
            Integer.parseInt(entrada);
            return true;
        }catch(Exception e){
            System.out.println("Numero no valido [0-9]");
            return false;
        }
    }
}
class Tirada {
    protected byte bienPosicionados = 0;
    protected byte malPosicionados = 0;
    protected byte[] t_zero = {48,48,48,48,48};
    protected byte[] t_Usuario = {48,48,48,48,48};
    public Tirada(){}
    public Tirada (String aleatorio,String t_Usuario){
        byte[] t_Aleatorio = new byte[5];
        for (int i  = 0; i < t_Aleatorio.length;i++){
            t_Aleatorio[i] = (byte)(aleatorio.charAt(i));
            this.t_Usuario[i] = (byte)(t_Usuario.charAt(i));
        }
        compruebaBienPosicionados(t_Aleatorio);
        compruebaMalPosicionados(t_Aleatorio);
    }
    /************************COMPROBACION NUMEROS ALEATORIOS*****************************/
    private  void compruebaBienPosicionados(byte[] t_Aleatorio){
        for(int i = 0; i < t_Aleatorio.length;i++){
            if(t_Aleatorio[i] == this.t_Usuario[i]){
                this.t_zero[i] = 49;
                this.bienPosicionados++;
            }
        }
    }
    private  void compruebaMalPosicionados(byte[] t_Aleatorio){
        for(int i = 0; i < t_Aleatorio.length;i++){
            if(this.t_zero[i] != 49){
                for(int j = 0; j < t_Usuario.length;j++){
                    if(this.t_Usuario[j] == t_Aleatorio[i] && this.t_zero[j] == 48){
                        this.t_zero[j] = 50;
                        this.malPosicionados++;
                        break;
                    }
                }
            }
        }
    }
    /************************METODOS SOBREESCRITOS*****************************/
    public String toString(){
        return new String(this.t_Usuario) + "\t" +
                this.bienPosicionados + "\t" +
                this.malPosicionados+"\t"+new String(t_zero);
    }
}
class Partida implements I_Partida {
    protected byte[] aleatorio = new byte[5];
    protected ArrayList<Tirada> tiradas = new ArrayList<>();
    public Partida(){
        this.aleatorio = generaTablaAleatorios().getBytes();
    }
    private String generaTablaAleatorios(){
        int[] resultado = new int[5];
        String str = "";
        Random rd = new Random();
        for(int i = 0; i < resultado.length;i++){
            resultado[i] = rd.nextInt(9-0+1)+0;
            str+=resultado[i];
        }
        return str;
    }

    @Override
    public void afeguirTirada(String entrada) {
        tiradas.add(new Tirada(new String(aleatorio),entrada));
    }

    @Override
    public boolean comprobarTirada(String entrada) {
        return new String(aleatorio).equals(entrada);
    }
    @Override
    public String toString(){
        return this.toString(false);
    }
    public String toString(boolean dificil){
        StringBuilder bd = new StringBuilder();
        for (Tirada t1 : tiradas){
            if(dificil)bd.append(" "+t1.toString().substring(0,t1.toString().lastIndexOf("\t"))+"\n");
            else {
                bd.append(" "+t1.toString()+"\n");
            }
        }
        return bd.toString();
    }

}
interface I_Partida {
    /*
     * Metodo que permite agregar una tirada a una partida
     * Como argumento recibe un numero correcto introducido de la tirada
     * */
    public void afeguirTirada(String entrada);
    /*
     * Metodo que permite comprovar el numero iuntroducito
     * concide el numero aleatorio
     * */
    public boolean comprobarTirada(String entrada);
    /*
     * Devuelve una cadena con toda la estructura de un partida
     * preparada para mostrar la pantalla
     * */
    public String toString();
}
class Partida_BD extends Partida {
    protected static String url = "jdbc:mysql://localhost:3306/";
    protected static String root_user = "root";
    protected static String root_pwd = "";
    protected static String default_BD = "MMCgrageda";
    protected static String default_User = "cgrageda";
    protected static String default_Pwd = "admin";
    private static Connection con = null;
    protected int idPartida;
    public Partida_BD(){
        super();
        crearBD_User();
        crearPartida();
    }
    public Partida_BD(int idPartida){
        this.idPartida = idPartida;
        String cad0 = "use "+default_BD;
        String cad1 = "select Usuario,correctos,incorrectos,numAyuda  from t_Tiradas where id_Partida =  "+this.idPartida;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.print("SELECCIONA BD : "+st.executeUpdate(cad0));
            aleatorio = obtieneNumAleatorio().getBytes();
            rs = st.executeQuery(cad1);
            while (rs.next()){
                Tirada_BD t_MM_BD = new Tirada_BD(rs.getString("Usuario"),rs.getString("numAyuda").getBytes());
                this.tiradas.add(t_MM_BD);
            }
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            System.out.print("No se han realizado Partidas");
        }
    }
    public String obtieneNumAleatorio(){
        String cad0 = "use "+default_BD;
        String cad1 = "select numAleatorio from t_Partida where id_Partida = "+this.idPartida;
        String numAleatorio = "";
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println("SELECCIONA BD : "+st.executeUpdate(cad0));
            ResultSet rs = st.executeQuery(cad1);
            while (rs.next()){
                numAleatorio = rs.getString("numAleatorio");
            }
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
        return numAleatorio;
    }
    public  String crearBD_User(){
        String cad0,cad1,cad2;
        cad1= "create user if not exists \'" + default_User + "\'@\'localhost\' identified by \'" + default_Pwd + "\'";
        cad0 = "create database if not exists " + default_BD;
        cad2 = "grant all privileges on " + default_BD + ".* to \'" + default_User + "\'@\'localhost\'" +"identified by \'" + default_Pwd + "\' with grant option";
        try {
            //carga el controlador
            Class.forName("com.mysql.jdbc.Driver");
            //crea objeto de conexion
            con = DriverManager.getConnection(url,root_user,root_pwd);
            //crea objetos para ejecutar Sentencias SQL
            Statement st = con.createStatement();
            //Crea BD,USUARO, PROPORCIONA PERMISOS AL USUARIO
            System.out.println("CREA BD  : " + st.executeUpdate(cad0));
            System.out.println("CREA USUARIO  : " + st.executeUpdate(cad1));
            System.out.println("CREA ASIGNA PERMISOS  : " + st.executeUpdate(cad2));
            crearTablaPartida();
            if (con != null) con.close();
            if (st != null)st.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            mostraSQLException(e);
            return "No se ha podido crear la Base de datos o El usuario";
        }
        return "Todo ha salido bien !!!!";
    }

    private void crearTablaPartida(){
        String cad0 = "use "+default_BD;
        String cad1 = "create table if not exists t_Partida " +"(" +
                "id_Partida int auto_increment  primary key not null,"+
                "numAleatorio varchar(10) not null," +
                "Terminado BOOLEAN not null default 0)";
        String cad2 = "create table if not exists t_Tiradas " +"(" +
                "id_Tirada int auto_increment  primary key not null,"+
                "correctos int not null," +
                "incorrectos int not null," +
                "numAyuda varchar(10) not null," +
                "Usuario varchar(10) not null," +
                "id_Partida int," +
                "foreign key (id_Partida) references t_Partida(id_Partida) on update cascade)";
        try {
            //crea objeto de conexion
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            //crea objetos para ejecutar Sentencias SQL
            Statement st = con.createStatement();
            //Crea BD,USUARO, PROPORCIONA PERMISOS AL USUARIO
            System.out.println("SELECCIONA BD  : "+st.executeUpdate(cad0));
            System.out.println("CREA UNA TABLA PARTIDA : "+st.executeUpdate(cad1));
            System.out.println("CREA UNA TABLA TIRADA : "+st.executeUpdate(cad2));
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
    }
    public void crearPartida(){
        String cad0 = "use "+default_BD;
        String cad1 = "insert into t_Partida (numAleatorio) " +
                "values ('"+new String(this.aleatorio)+"')";
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println(" SELECCIONA BD  : "+st.executeUpdate(cad0));
            System.out.println(" AGREGA NUEVA PARTIDA : "+st.executeUpdate(cad1,Statement.RETURN_GENERATED_KEYS));
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next())this.idPartida = rs.getInt(1);
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
    }
    public void agregarTirada(Tirada_BD obj){
        String cad0 = "use "+default_BD;
        String cad1 = "insert into t_Tiradas(correctos,incorrectos,numAyuda,Usuario,id_Partida) values("+obj.getBienPosicionados()+","+obj.getMalPosicionados()+",'"+obj.getT_Zero()+
                "','"+obj.getT_Usuario()+"',"+this.idPartida+")";
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println("SELECCIONA BD : "+st.executeUpdate(cad0));
            System.out.println("AGREGA TIRADA A TABLA TIRADAS : "+st.executeUpdate(cad1));
            con.close();
            st.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
    }
    public static String consultar_Partida(){
        String cad0 = "use "+default_BD;
        String cad1 = "select id_Partida,Terminado from t_Partida";
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.print("SELECCIONA BD : "+st.executeUpdate(cad0));
            rs = st.executeQuery(cad1);
            while (rs.next()){
                String finish_Game = "";
                if (rs.getBoolean("Terminado"))finish_Game = "\tTERMINADA";
                else finish_Game = "\tEN CURSO";
                sb.append("\t"+rs.getString("id_Partida")+finish_Game+"\n");
            }
            rs.close();
            con.close();
            st.close();
        }catch (SQLException e){
            return "\tAun no Se han Jugado Partidas";
        }
        return sb.toString();
    }
    public String consultar_Tiradas(int idPartida){
        String cad0 = "use "+default_BD;
        String cad2 = "select * from t_Tiradas where id_Partida = "+idPartida;
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println("SELECCIONA BD : "+st.executeUpdate(cad0));
            rs = st.executeQuery(cad2);
            while (rs.next()){
                sb.append("  "+rs.getString(5)+"\t"+rs.getString(4)+"\t"+rs.getString(3)+"\t"+rs.getString(2)+"\n");
            }
            rs.close();
            con.close();
            st.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
        return sb.toString();
    }
    public void update_Terminado(boolean terminado){
        String cad0 = "use "+default_BD;
        String cad1 = "update t_Partida set Terminado = "+terminado+" where id_Partida = "+idPartida;
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println("SELECCIONA BD : "+st.executeUpdate(cad0));
            System.out.println("ACTUALIZA ESTADO PARTIDA : "+st.executeUpdate(cad1));
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
    }
    public static void mostraSQLException(SQLException ex){
        ex.printStackTrace();
        System.err.println("SQL STATE : "+ex.getSQLState());
        System.err.println("ERROR CODE : "+ex.getErrorCode());
        System.err.println("MESSEGE  : "+ex.getMessage());
        Throwable t = ex.getCause();
        while (t != null){
            System.out.println("CAUSE  : "+t);
            t = t.getCause();
        }
    }
    public int getNumTirada(){
        int resultado = 0;
        String cad0 = "use "+default_BD;
        String cad1 = "select count(id_Tirada) from t_Tiradas where id_Partida = "+this.idPartida;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println("SELECCIONA DATABASE : "+st.executeUpdate(cad0));
            rs = st.executeQuery(cad1);
            if (rs.next())resultado = rs.getInt(1);
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
        return resultado;
    }
    public boolean getTerminado(){

        String cad0 = "use "+default_BD;
        String cad1 = "select Terminado from t_Partida where id_Partida = "+this.idPartida;
        boolean resultado = false;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url,default_User,default_Pwd);
            Statement st = con.createStatement();
            System.out.println("SELECCIONA DATABASE : "+st.executeUpdate(cad0));
            rs = st.executeQuery(cad1);
            if (rs.next())resultado = rs.getBoolean("Terminado");
            if(st!=null) st.close();
            if(con!=null) con.close();
        }catch (SQLException e){
            mostraSQLException(e);
        }
        return resultado;
    }


    @Override
    public void afeguirTirada(String entrada) {
        super.afeguirTirada(entrada);
        agregarTirada(new Tirada_BD(new String(this.aleatorio),entrada));
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String toString(boolean dificil) {
        return super.toString(dificil);
    }
}
class Tirada_BD extends Tirada {

    public Tirada_BD(){ }
    public Tirada_BD(String numAleatorio, String numUsuario){
        super(numAleatorio,numUsuario);
    }
    public Tirada_BD(String usuario, byte[] resultado){
        this.t_Usuario = usuario.getBytes();
        this.t_zero = resultado;
    }
    public String getT_Usuario(){
        return new String(t_Usuario);
    }
    public String getT_Zero(){
        return new String(t_zero);
    }
    public int getBienPosicionados(){
        return bienPosicionados;
    }
    public int getMalPosicionados(){
        return malPosicionados;
    }

}


