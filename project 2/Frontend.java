// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: Red
// Role: Frontend
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;


public class Frontend extends JFrame {
    BackEndCode b = new BackEndCode("flights.csv");

    /**
     * Constructor to initialize the menu interface of the frontend
     *
     * @throws IOException
     * @throws DataFormatException
     */
    public Frontend() throws IOException, DataFormatException {
        initComponents();
    }

    /**
     * Main that is run which creates an instance of the GUI along with making the JFrame visible.
     *
     * @param args
     * @throws IOException
     * @throws DataFormatException
     */
    public static void main(String[] args) throws IOException, DataFormatException {
        Frontend run = new Frontend();
        run.setVisible(true);
    }

    /**
     * Method that listens for mouse click over the Add Flight button which sends the data in the
     * textfields to the backend
     *
     * @param e1 when the "Add Flight" button is clicked
     */
    protected void addFlightMouseClicked(MouseEvent e1) {
        try {
            b.addFlights(Integer.parseInt(addFN.getText()), addAir.getText(),
                Double.parseDouble(addPPM.getText()), addDep.getText(), addDes.getText(),
                addDate.getText());
        } catch (Exception e) {
            // Invalid inputs
        }
    }

    /**
     * This method listens for a mouse click on the search button on the interface. When clicked,
     * the method attempts to run several helper methods to send user entered parameters to backend.
     * This would allow flights to be displayed on the interface after the backend returns the list
     * of correct flights to the frontend.
     *
     * @param e mouse click for the search button
     */
    protected void SearchMouseClicked(MouseEvent e) {
        for (int i = 0; i < 50; i++) {
            fNumber[i].setText("");
            sLocations[i].setText("");
            eLocations[i].setText("");
            costOfFlight[i].setText("");
            when[i].setText("");
            aIcon[i].setIcon(null);
        }
        boolean valid = true;
        try {
            locationFilter(Location.getText(), Location2.getText());
        } catch (NoSuchElementException e1) {
            valid = false;
            // Invalid locations
        }
        try {
            dateFilter(Date.getText());
        } catch (IllegalArgumentException e2) {
            valid = false;
        }
        if (!Min.getText().equals("") || !Max.getText().equals(""))
            try {
                priceFilter(Double.parseDouble(Min.getText()), Double.parseDouble(Max.getText()));
            } catch (IllegalArgumentException e3) {
                valid = false;
            }
        else {
            priceFilter(0,999999);
        }
        if (valid) {
            List<FlightInterface> Flights = b.getAllFlights();
            for (int i = 0; i < Flights.size(); i++) {
                fNumber[i].setText(Integer.toString(Flights.get(i).getFlightNumber()));
                sLocations[i].setText(Flights.get(i).getStart());
                eLocations[i].setText(Flights.get(i).getDestination());
                costOfFlight[i].setText("$" + (new DecimalFormat("#.##").format(b.getPrice(
                    b.getDistance(Flights.get(i).getStartCoordinates().get(0),
                        Flights.get(i).getStartCoordinates().get(1),
                        Flights.get(i).getDestinationCoordinates().get(0),
                        Flights.get(i).getDestinationCoordinates().get(1)),
                    Flights.get(i).getPricePerMile()))));
                when[i].setText(Flights.get(i).getDate());
                if (Flights.get(i).getAirline().equals("Delta")) {
                    aIcon[i].setIcon(new ImageIcon(getClass().getResource("/assets/delta.png")));
                } else if (Flights.get(i).getAirline().equals("Spirit")) {
                    aIcon[i].setIcon(new ImageIcon(getClass().getResource("/assets/spirit.png")));
                } else if (Flights.get(i).getAirline().equals("American")) {
                    aIcon[i]
                        .setIcon(new ImageIcon(getClass().getResource("/assets/aairlines.png")));
                } else if (Flights.get(i).getAirline().equals("Southwest")) {
                    aIcon[i]
                        .setIcon(new ImageIcon(getClass().getResource("/assets/southwest.png")));
                } else if (Flights.get(i).getAirline().equals("JetBlue")) {
                    aIcon[i].setIcon(new ImageIcon(getClass().getResource("/assets/jetblue.png")));
                }
            }
        }
    }

    /**
     * Takes locations and sends them to the backend
     *
     * @param start the departure location
     * @param end the destination location
     */
    protected void locationFilter(String start, String end) {
        String[] locations =
            {"Chicago", "New York", "Los Angeles", "Houston", "Las Vegas", "Atlanta", "Toronto"};
        boolean contains = false;
        String duplicate = "";
        for (int i = 0; i < locations.length; i++) {
            if (start.equalsIgnoreCase(locations[i])) {
                contains = true;
                duplicate = locations[i];
                locations[i] = null;
                break;
            }
        }
        if (!contains)
            throw new NoSuchElementException("Invalid Location");
        contains = false;
        for (int i = 0; i < locations.length; i++) {
            if (end.equalsIgnoreCase(locations[i])) {
                contains = true;
                break;
            }
        }
        if (!contains)
            throw new NoSuchElementException("Invalid Location");

        b.setStart(start);
        b.setDestination(end);
    }

    /**
     * Takes a date string and sends to backend
     *
     * @param date string of a date format
     */
    protected void dateFilter(String date) {
        try {
            String[] sep = date.split("/", 3);
            if (Integer.parseInt(sep[0]) > 12 || Integer.parseInt(sep[0]) < 1) {
                throw new Exception("Month error");
            }
            if (Integer.parseInt(sep[1]) > 31 || Integer.parseInt(sep[1]) < 1) {
                throw new Exception("Day error");
            }
            if (Integer.parseInt(sep[2]) > 99|| Integer.parseInt(sep[2]) < 0) {
                throw new Exception("Year error");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Date is invalid");
        }
        b.addDate(date);
    }

    /**
     * Takes in prices from the interface and sends them as minimum and maximum price to backend
     *
     * @param min the smallest price a flight should be
     * @param max the largest price a flight should be
     */
    protected void priceFilter(double min, double max) {
        if (min < 0)
            throw new IllegalArgumentException("Minimum cannot be less than $0.00");
        else if (min >= max)
            throw new IllegalArgumentException("Minimum cannot be less than the maximum");
        else {
            b.setMinPrice(min);
            b.setMaxPrice(max);
        }
    }

    /**
     * Method that adds all graphical components to the JFrame.
     *
     */
    private void initComponents() {
        Menu = new JLayeredPane();
        usd = new JLabel();
        usd2 = new JLabel();
        Max = new JTextField();
        to = new JLabel();
        Textfield5 = new JLabel();
        Min = new JTextField();
        Textfield4 = new JLabel();
        TextFilt = new JLabel();
        Search = new JLabel();
        Departure = new JLabel();
        Location = new JTextField();
        Textfield2 = new JLabel();
        Arrival = new JLabel();
        Location2 = new JTextField();
        Textfield1 = new JLabel();
        calendar = new JLabel();
        Date = new JTextField();
        Textfield3 = new JLabel();
        Logo = new JLabel();
        Filter = new JLabel();
        leftElement = new JLabel();
        rightElement = new JLabel();
        Background = new JLabel();
        Number = new JLabel();
        Airline = new JLabel();
        Start = new JLabel();
        End = new JLabel();
        Date1 = new JLabel();
        Price = new JLabel();
        rightElement2 = new JLabel();
        addFN = new JTextField();
        addAir = new JTextField();
        addPPM = new JTextField();
        addDep = new JTextField();
        addDes = new JTextField();
        addDate = new JTextField();
        FNText = new JLabel();
        AirText = new JLabel();
        PPMText = new JLabel();
        DepText = new JLabel();
        DesText = new JLabel();
        DateText = new JLabel();
        Ext = new JLabel();
        addFlight = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/assets/planeicon.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project RED");
        setResizable(false);
        var contentPane = getContentPane();

        //======== Menu ========
        {
            //---- All airlines ----
            for (int i = 0; i < 50; i++) {
                aIcon[i] = new JLabel();
                Menu.add(aIcon[i], JLayeredPane.DEFAULT_LAYER);
                aIcon[i].setBounds(385, (50 + (i * 25)), 130, 45);
            }

            //---- All flight numbers ----
            for (int i = 0; i < 50; i++) {
                fNumber[i] = new JLabel();
                fNumber[i].setFont(new Font("Georgia", Font.BOLD, 18));
                fNumber[i].setForeground(Color.WHITE);
                Menu.add(fNumber[i], JLayeredPane.DEFAULT_LAYER);
                fNumber[i].setBounds(530, (50 + (i * 25)), 75, 45);
            }

            //---- All flight starts ----
            for (int i = 0; i < 50; i++) {
                sLocations[i] = new JLabel();
                sLocations[i].setFont(new Font("Georgia", Font.BOLD, 18));
                sLocations[i].setForeground(Color.WHITE);
                Menu.add(sLocations[i], JLayeredPane.DEFAULT_LAYER);
                sLocations[i].setBounds(670, (50 + (i * 25)), 200, 45);
            }

            //---- All flight ends ----
            for (int i = 0; i < 50; i++) {
                eLocations[i] = new JLabel();
                eLocations[i].setFont(new Font("Georgia", Font.BOLD, 18));
                eLocations[i].setForeground(Color.WHITE);
                Menu.add(eLocations[i], JLayeredPane.DEFAULT_LAYER);
                eLocations[i].setBounds(800, (50 + (i * 25)), 200, 45);
            }

            //---- All flight dates ----
            for (int i = 0; i < 50; i++) {
                when[i] = new JLabel();
                when[i].setFont(new Font("Georgia", Font.BOLD, 18));
                when[i].setForeground(Color.WHITE);
                Menu.add(when[i], JLayeredPane.DEFAULT_LAYER);
                when[i].setBounds(940, (50 + (i * 25)), 200, 45);
            }

            //---- All flight prices ----
            for (int i = 0; i < 50; i++) {
                costOfFlight[i] = new JLabel();
                costOfFlight[i].setFont(new Font("Georgia", Font.BOLD, 18));
                costOfFlight[i].setForeground(Color.WHITE);
                Menu.add(costOfFlight[i], JLayeredPane.DEFAULT_LAYER);
                costOfFlight[i].setBounds(1050, (50 + (i * 25)), 200, 45);
            }



            //---- airline ----
            Airline.setText("Airline");
            Airline.setFont(new Font("Georgia", Font.BOLD, 20));
            Airline.setForeground(Color.BLACK);
            Menu.add(Airline, JLayeredPane.DEFAULT_LAYER);
            Airline.setBounds(375, 10, 75, 55);

            //---- Number ----
            Number.setText("Flight Number");
            Number.setFont(new Font("Georgia", Font.BOLD, 20));
            Number.setForeground(Color.BLACK);
            Menu.add(Number, JLayeredPane.DEFAULT_LAYER);
            Number.setBounds(475, 10, 200, 55);

            //---- start ----
            Start.setText("Departure");
            Start.setFont(new Font("Georgia", Font.BOLD, 20));
            Start.setForeground(Color.BLACK);
            Menu.add(Start, JLayeredPane.DEFAULT_LAYER);
            Start.setBounds(655, 10, 120, 55);

            //---- end ----
            End.setText("Destination");
            End.setFont(new Font("Georgia", Font.BOLD, 20));
            End.setForeground(Color.BLACK);
            Menu.add(End, JLayeredPane.DEFAULT_LAYER);
            End.setBounds(785, 10, 200, 55);

            //---- date1 ----
            Date1.setText("Date");
            Date1.setFont(new Font("Georgia", Font.BOLD, 20));
            Date1.setForeground(Color.BLACK);
            Menu.add(Date1, JLayeredPane.DEFAULT_LAYER);
            Date1.setBounds(955, 10, 120, 55);

            //---- date1 ----
            Price.setText("Price");
            Price.setFont(new Font("Georgia", Font.BOLD, 20));
            Price.setForeground(Color.BLACK);
            Menu.add(Price, JLayeredPane.DEFAULT_LAYER);
            Price.setBounds(1055, 10, 120, 55);

            //---- usd ----
            usd.setIcon(new ImageIcon(getClass().getResource("/assets/usd.png")));
            Menu.add(usd, JLayeredPane.DEFAULT_LAYER);
            usd.setBounds(40, 515, 30, 31);

            //---- usd2 ----
            usd2.setIcon(new ImageIcon(getClass().getResource("/assets/usd.png")));
            Menu.add(usd2, JLayeredPane.DEFAULT_LAYER);
            usd2.setBounds(180, 515, 30, 31);

            //---- Max ----
            Max.setBackground(Color.white);
            Max.setColumns(50);
            Max.setForeground(Color.black);
            Max.setFont(new Font("Georgia", Font.BOLD, 14));
            Max.setBorder(null);
            Menu.add(Max, JLayeredPane.DEFAULT_LAYER);
            Max.setBounds(205, 520, 80, 20);

            //---- to ----
            to.setText("-");
            to.setFont(new Font("Georgia", Font.BOLD, 18));
            to.setForeground(Color.BLACK);
            Menu.add(to, JLayeredPane.DEFAULT_LAYER);
            to.setBounds(165, 505, 40, 45);

            //---- Textfield5 ----
            Textfield5.setIcon(new ImageIcon(getClass().getResource("/assets/textfield2.png")));
            Menu.add(Textfield5, JLayeredPane.DEFAULT_LAYER);
            Textfield5.setBounds(175, 510, 372, 45);

            //---- Min ----
            Min.setBackground(Color.white);
            Min.setColumns(50);
            Min.setForeground(Color.black);
            Min.setFont(new Font("Georgia", Font.BOLD, 14));
            Min.setBorder(null);
            Menu.add(Min, JLayeredPane.DEFAULT_LAYER);
            Min.setBounds(65, 520, 80, 20);

            //---- Textfield4 ----
            Textfield4.setIcon(new ImageIcon(getClass().getResource("/assets/textfield2.png")));
            Menu.add(Textfield4, JLayeredPane.DEFAULT_LAYER);
            Textfield4.setBounds(35, 510, 372, 45);

            //---- TextFilt ----
            TextFilt.setText("Filters");
            TextFilt.setFont(new Font("Georgia", Font.BOLD, 20));
            TextFilt.setForeground(Color.BLACK);
            Menu.add(TextFilt, JLayeredPane.DEFAULT_LAYER);
            TextFilt.setBounds(70, 470, 160, TextFilt.getPreferredSize().height);

            //---- Search ----
            Search.setIcon(new ImageIcon(getClass().getResource("/assets/Search.png")));
            Search.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    SearchMouseClicked(e);
                }
            });
            Menu.add(Search, JLayeredPane.DEFAULT_LAYER);
            Search.setBounds(95, 345, 135, 55);

            //---- Departure ----
            Departure.setIcon(new ImageIcon(getClass().getResource("/assets/departure.png")));
            Menu.add(Departure, JLayeredPane.DEFAULT_LAYER);
            Departure.setBounds(280, 165, 30, 25);

            //---- Location ----
            Location.setBackground(Color.white);
            Location.setColumns(50);
            Location.setForeground(Color.black);
            Location.setFont(new Font("Georgia", Font.BOLD, 14));
            Location.setBorder(null);
            Location.setText("i.e. Toronto");
            Menu.add(Location, JLayeredPane.DEFAULT_LAYER);
            Location.setBounds(25, 165, 250, 28);

            //---- Textfield2 ----
            Textfield2.setIcon(new ImageIcon(getClass().getResource("/assets/textfield.png")));
            Menu.add(Textfield2, JLayeredPane.DEFAULT_LAYER);
            Textfield2.setBounds(-10, 155, 372, 45);

            //---- Arrival ----
            Arrival.setIcon(new ImageIcon(getClass().getResource("/assets/Arrival.png")));
            Menu.add(Arrival, JLayeredPane.DEFAULT_LAYER);
            Arrival.setBounds(280, 227, 30, Arrival.getPreferredSize().height);

            //---- Location2 ----
            Location2.setBackground(Color.white);
            Location2.setColumns(50);
            Location2.setForeground(Color.black);
            Location2.setFont(new Font("Georgia", Font.BOLD, 14));
            Location2.setBorder(null);
            Location2.setText("i.e. Chicago");
            Menu.add(Location2, JLayeredPane.DEFAULT_LAYER);
            Location2.setBounds(25, 225, 250, 25);

            //---- Textfield1 ----
            Textfield1.setIcon(new ImageIcon(getClass().getResource("/assets/textfield.png")));
            Menu.add(Textfield1, JLayeredPane.DEFAULT_LAYER);
            Textfield1.setBounds(-10, 215, 372, 45);

            //---- calendar ----
            calendar.setIcon(new ImageIcon(getClass().getResource("/assets/calendar.png")));
            Menu.add(calendar, JLayeredPane.DEFAULT_LAYER);
            calendar.setBounds(280, 287, 30, 20);

            //---- Date ----
            Date.setBackground(Color.white);
            Date.setColumns(50);
            Date.setForeground(Color.black);
            Date.setFont(new Font("Georgia", Font.BOLD, 14));
            Date.setBorder(null);
            Date.setText("mm/dd/yy");
            Menu.add(Date, JLayeredPane.DEFAULT_LAYER);
            Date.setBounds(25, 285, 250, 25);

            //---- Textfield3 ----
            Textfield3.setIcon(new ImageIcon(getClass().getResource("/assets/textfield.png")));
            Menu.add(Textfield3, JLayeredPane.DEFAULT_LAYER);
            Textfield3.setBounds(-10, 275, 372, 45);

            //---- Logo ----
            Logo.setIcon(new ImageIcon(getClass().getResource("/assets/logop.png")));
            Menu.add(Logo, JLayeredPane.DEFAULT_LAYER);
            Logo.setBounds(110, 15, 140, 140);

            //---- Filter ----
            Filter.setIcon(new ImageIcon(getClass().getResource("/assets/filte.png")));
            Menu.add(Filter, JLayeredPane.DEFAULT_LAYER);
            Filter.setBounds(30, 460, 40, 41);

            //---- leftElement ----
            leftElement.setBackground(new Color(0, 0, 0, 170));
            leftElement.setIcon(new ImageIcon(getClass().getResource("/assets/leftElement.png")));
            Menu.add(leftElement, JLayeredPane.DEFAULT_LAYER);
            leftElement.setBounds(0, 0, 355, 720);

            //---- Ext ----
            Ext.setText("External Flight Tool");
            Ext.setFont(new Font("Georgia", Font.BOLD, 25));
            Ext.setForeground(Color.BLACK);
            Menu.add(Ext, JLayeredPane.DEFAULT_LAYER);
            Ext.setBounds(640, 485, 500, 100);

            //---- addFN ----
            addFN.setBackground(Color.white);
            addFN.setColumns(50);
            addFN.setForeground(Color.black);
            addFN.setFont(new Font("Georgia", Font.BOLD, 10));
            addFN.setBorder(null);
            addFN.setText("Flight Number");
            Menu.add(addFN, JLayeredPane.DEFAULT_LAYER);
            addFN.setBounds(500, 570, 115, 15);

            //---- FNTF ----
            FNText.setIcon(new ImageIcon(getClass().getResource("/assets/textfield3.png")));
            Menu.add(FNText, JLayeredPane.DEFAULT_LAYER);
            FNText.setBounds(495, 565, 130, 25);

            //---- addAir ----
            addAir.setBackground(Color.white);
            addAir.setColumns(50);
            addAir.setForeground(Color.black);
            addAir.setFont(new Font("Georgia", Font.BOLD, 10));
            addAir.setBorder(null);
            addAir.setText("Airline");
            Menu.add(addAir, JLayeredPane.DEFAULT_LAYER);
            addAir.setBounds(720, 570, 115, 15);

            //---- AirTF ----
            AirText.setIcon(new ImageIcon(getClass().getResource("/assets/textfield3.png")));
            Menu.add(AirText, JLayeredPane.DEFAULT_LAYER);
            AirText.setBounds(715, 565, 130, 25);

            //---- addPPM ----
            addPPM.setBackground(Color.white);
            addPPM.setColumns(50);
            addPPM.setForeground(Color.black);
            addPPM.setFont(new Font("Georgia", Font.BOLD, 10));
            addPPM.setBorder(null);
            addPPM.setText("Price Per Mile");
            Menu.add(addPPM, JLayeredPane.DEFAULT_LAYER);
            addPPM.setBounds(940, 570, 115, 15);

            //---- PPMTF ----
            PPMText.setIcon(new ImageIcon(getClass().getResource("/assets/textfield3.png")));
            Menu.add(PPMText, JLayeredPane.DEFAULT_LAYER);
            PPMText.setBounds(935, 565, 130, 25);

            //---- addDep ----
            addDep.setBackground(Color.white);
            addDep.setColumns(50);
            addDep.setForeground(Color.black);
            addDep.setFont(new Font("Georgia", Font.BOLD, 10));
            addDep.setBorder(null);
            addDep.setText("Departure");
            Menu.add(addDep, JLayeredPane.DEFAULT_LAYER);
            addDep.setBounds(500, 615, 115, 15);

            //---- DepTF ----
            DepText.setIcon(new ImageIcon(getClass().getResource("/assets/textfield3.png")));
            Menu.add(DepText, JLayeredPane.DEFAULT_LAYER);
            DepText.setBounds(495, 610, 130, 25);

            //---- addDes ----
            addDes.setBackground(Color.white);
            addDes.setColumns(50);
            addDes.setForeground(Color.black);
            addDes.setFont(new Font("Georgia", Font.BOLD, 10));
            addDes.setBorder(null);
            addDes.setText("Destination");
            Menu.add(addDes, JLayeredPane.DEFAULT_LAYER);
            addDes.setBounds(720, 615, 115, 15);

            //---- DesTF ----
            DesText.setIcon(new ImageIcon(getClass().getResource("/assets/textfield3.png")));
            Menu.add(DesText, JLayeredPane.DEFAULT_LAYER);
            DesText.setBounds(715, 610, 130, 25);

            //---- addDate ----
            addDate.setBackground(Color.white);
            addDate.setColumns(50);
            addDate.setForeground(Color.black);
            addDate.setFont(new Font("Georgia", Font.BOLD, 10));
            addDate.setBorder(null);
            addDate.setText("Date");
            Menu.add(addDate, JLayeredPane.DEFAULT_LAYER);
            addDate.setBounds(940, 615, 115, 15);

            //---- DesTF ----
            DateText.setIcon(new ImageIcon(getClass().getResource("/assets/textfield3.png")));
            Menu.add(DateText, JLayeredPane.DEFAULT_LAYER);
            DateText.setBounds(935, 610, 130, 25);

            //---- addFlight ----
            addFlight.setIcon(new ImageIcon(getClass().getResource("/assets/addflight.png")));
            addFlight.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e1) {
                    addFlightMouseClicked(e1);
                }
            });
            Menu.add(addFlight, JLayeredPane.DEFAULT_LAYER);
            addFlight.setBounds(712, 650, 135, 55);

            //---- rightElement2 ----
            rightElement2
                .setIcon(new ImageIcon(getClass().getResource("/assets/rightElement2.png")));
            Menu.add(rightElement2, JLayeredPane.DEFAULT_LAYER);
            rightElement2.setBounds(335, 0, 940, 720);

            //---- rightElement ----
            rightElement.setBackground(new Color(0, 0, 0, 170));
            rightElement.setIcon(new ImageIcon(getClass().getResource("/assets/rightElement.png")));
            Menu.add(rightElement, JLayeredPane.DEFAULT_LAYER);
            rightElement.setBounds(335, 0, 940, 720);


            //---- Background ----
            Background.setIcon(new ImageIcon(getClass().getResource("/assets/background.png")));
            Menu.add(Background, JLayeredPane.DEFAULT_LAYER);
            Background.setBounds(0, 0, 1280, 720);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(contentPaneLayout.createParallelGroup().addGroup(
            contentPaneLayout.createSequentialGroup().addGap(0, 0, 0)
                .addComponent(Menu, GroupLayout.PREFERRED_SIZE, 1280, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        contentPaneLayout.setVerticalGroup(contentPaneLayout.createParallelGroup()
            .addGroup(GroupLayout.Alignment.TRAILING,
                contentPaneLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Menu, GroupLayout.PREFERRED_SIZE, 720,
                        GroupLayout.PREFERRED_SIZE)));
        setSize(1280, 750);
        setLocationRelativeTo(null);
    }
    private JLabel FNText;
    private JLabel AirText;
    private JLabel PPMText;
    private JLabel DepText;
    private JLabel DesText;
    private JLabel DateText;
    private JLabel Ext;
    private JLabel addFlight;
    private JTextField addFN;
    private JTextField addAir;
    private JTextField addPPM;
    private JTextField addDep;
    private JTextField addDes;
    private JTextField addDate;
    private JLabel[] aIcon = new JLabel[50];
    private JLabel[] fNumber = new JLabel[50];
    private JLabel[] sLocations = new JLabel[50];
    private JLabel[] eLocations = new JLabel[50];
    private JLabel[] when = new JLabel[50];
    private JLabel[] costOfFlight = new JLabel[50];
    private JLabel Number;
    private JLabel Airline;
    private JLabel Start;
    private JLabel End;
    private JLabel Date1;
    private JLabel Price;
    private JLayeredPane Menu;
    private JLabel rightElement2;
    private JLabel usd;
    private JLabel usd2;
    private JTextField Max;
    private JLabel to;
    private JLabel Textfield5;
    private JTextField Min;
    private JLabel Textfield4;
    private JLabel TextFilt;
    private JLabel Search;
    private JLabel Departure;
    private JTextField Location;
    private JLabel Textfield2;
    private JLabel Arrival;
    private JTextField Location2;
    private JLabel Textfield1;
    private JLabel calendar;
    private JTextField Date;
    private JLabel Textfield3;
    private JLabel Logo;
    private JLabel Filter;
    private JLabel leftElement;
    private JLabel rightElement;
    private JLabel Background;
}

