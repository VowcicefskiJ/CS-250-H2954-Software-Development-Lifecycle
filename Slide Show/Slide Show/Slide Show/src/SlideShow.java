import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

public class SlideShow extends JFrame {

	private static final long serialVersionUID = 1L;

	// GUI components for displaying slides, text, and navigation buttons
    private JPanel slidePane;
    private JPanel textPane;
    private JPanel buttonPane;
    private CardLayout card;
    private CardLayout cardText;
    private JButton btnPrev;
    private JButton btnNext;
    private JLabel lblSlide;
    private JLabel lblTextArea;

    // URLs for booking - each URL corresponds to a slide in the slideshow
    private final String[] bookingUrls = {
        "https://www.expedia.com/Parrot-Cay-Hotels-COMO-Parrot-Cay.h551998.Hotel-Information",
        "https://www.expedia.com/Phoenix-Hotels-CIVANA-Wellness-Resort-And-Spa.h841899.Hotel-Information",
        "https://www.cntraveler.com/hotels/harads/arctic-bath-hotel",
        "https://www.expedia.com/Sawai-Madhopur-Hotels-Six-Senses-Fort-Barwara.h67658966.Hotel-Information",
        "https://www.expedia.com/Sheboygan-Hotels-The-American-Club.h21295.Hotel-Information"
    };

    // Constructor to initialize the application
    public SlideShow() throws HeadlessException {
        initComponent();
    }

  //initialize the contents of the frame.
    private void initComponent() {
    	// Initialize layout managers and panels
        // CardLayout allows switching between different components
        card = new CardLayout();
        cardText = new CardLayout();
        slidePane = new JPanel();
        textPane = new JPanel();
        textPane.setBackground(Color.BLUE);
        textPane.setBounds(5, 470, 790, 50);
        textPane.setVisible(true);
        buttonPane = new JPanel();
        btnPrev = new JButton();
        btnNext = new JButton();
        lblSlide = new JLabel();
        lblTextArea = new JLabel();

        // Set up frame attributes
        // Size, location, title, and default close operation are set for the frame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Wellness Retreats SlideShow");
        getContentPane().setLayout(new BorderLayout(10, 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layouts for the panels
        // slidePane and textPane use CardLayout for displaying slides and text
        slidePane.setLayout(card);
        textPane.setLayout(cardText);
        
        // Add slides and text to the panels
        // For each slide, a JLabel is created for the image and text
        for (int i = 1; i <= 5; i++) {
            lblSlide = new JLabel();
            lblTextArea = new JLabel();
            lblSlide.setText(getResizeIcon(i));
            lblTextArea.setText(getTextDescription(i));
            slidePane.add(lblSlide, "card" + i);
            textPane.add(lblTextArea, "cardText" + i);

            // Add mouse listener to open booking URL on click
            // When a slide is clicked, the corresponding booking URL is opened
            int index = i - 1; // Adjust for array indexing
            lblSlide.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openWebpage(bookingUrls[index]);
                }
            });
        }
        // Add panels to the frame
        // slidePane is added to the center, textPane to the bottom (SOUTH)
        getContentPane().add(slidePane, BorderLayout.CENTER);
        getContentPane().add(textPane, BorderLayout.SOUTH);
        
        // Set up the navigation button panel
        // FlowLayout is used for evenly spacing the buttons
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnPrev.setText("Previous");
        // Add action listeners for navigation buttons
        // These listeners define the behavior when Previous or Next buttons are clicked
        btnPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                goPrevious();
            }
        });
        // Add buttons to the panel
        // Buttons are added to the buttonPane which is then added to the frame
        buttonPane.add(btnPrev);

        btnNext.setText("Next");
        btnNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                goNext();
            }
        });
        buttonPane.add(btnNext);

        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    // Navigate to the previous slide and text
    // This method is called when the Previous button is clicked
    private void goPrevious() {
        card.previous(slidePane);
        cardText.previous(textPane);
    }
    
    // Navigate to the next slide and text
    // This method is called when the Next button is clicked
    private void goNext() {
        card.next(slidePane);
        cardText.next(textPane);
    }

    // Get the image for a specific slide
    // This method returns an HTML string with the image path for each slide
    private String getResizeIcon(int i) {
        String image = ""; 
        switch (i) {
            case 1:
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/COMO Parrot Cay Turks and Caicos.jpg") + "'/></body></html>";
                break;
            case 2:
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Civana Wellness Resort and Spa Arizona.jpg") + "'/></body></html>";
                break;
            case 3:
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Arctic Bath Hotel Harads, Sweden.jpg") + "'/></body></html>";
                break;
            case 4:
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Six Senses Fort Barwara India.jpg") + "'/></body></html>";
                break;
            case 5:
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/The American Club Kohler, Wisconsin.jpg") + "'/></body></html>";
                break;
        }
        return image;
    }

    // Get the text description for a specific slide
    // This method returns an HTML string with the text description for each slide
    private String getTextDescription(int i) {
        String text = ""; 
        switch (i) {
            case 1:
                text = "<html><body><font size='5'>COMO Parrot Cay — Turks and Caicos.</font> <br>A luxurious retreat offering world-class yoga and wellness facilities.</body></html>";
                break;
            case 2:
                text = "<html><body><font size='5'>Civana Wellness Resort and Spa — Arizona.</font> <br>Immersive wellness in the Sonoran Desert with a variety of rejuvenating activities.</body></html>";
                break;
            case 3:
                text = "<html><body><font size='5'>Arctic Bath Hotel — Harads, Sweden.</font> <br>Fairytale-like wellness experience with Nordic spa traditions and stunning natural surroundings.</body></html>";
                break;
            case 4:
                text = "<html><body><font size='5'>Six Senses Fort Barwara — India.</font> <br>Ancient fortress turned wellness sanctuary offering Ayurveda, yoga, and spa treatments with regional ingredients.</body></html>";
                break;
            case 5:
                text = "<html><body><font size='5'>The American Club — Kohler, Wisconsin.</font> <br>An upscale resort offering a blend of modern luxury and historical significance, with comprehensive wellness facilities.</body></html>";
                break;
        }
        return text;
    }

    // Open a webpage using the desktop browser
    // This method is used to open the booking URL in the user's default browser
    private void openWebpage(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to launch the application
    // This method is the entry point of the program
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SlideShow ss = new SlideShow();
                ss.setVisible(true);
            }
        });
    }
}