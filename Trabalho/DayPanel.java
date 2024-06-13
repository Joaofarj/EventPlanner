import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayPanel extends JPanel {
    private JLabel dayLabel;
    private JTextArea eventArea;
    private JButton addButton;

    public DayPanel(int day) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Adiciona margens ao redor do painel

        // Painel superior que contém o número do dia e o botão "+"
        JPanel topPanel = new JPanel(new BorderLayout());
        dayLabel = new JLabel(String.valueOf(day), SwingConstants.LEFT);
        addButton = new JButton("+");
        addButton.setMargin(new Insets(1, 1, 1, 1)); // Define margens menores para o botão
        addButton.setPreferredSize(new Dimension(20, 20));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String event = JOptionPane.showInputDialog(DayPanel.this, "Adicione um evento:");
                if (event != null && !event.trim().isEmpty()) {
                    eventArea.append(event + "\n");
                }
            }
        });

        topPanel.add(dayLabel, BorderLayout.WEST);
        topPanel.add(addButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Configura a área de texto para eventos
        eventArea = new JTextArea();
        eventArea.setLineWrap(true);
        eventArea.setWrapStyleWord(true);
        eventArea.setEditable(false);
        add(new JScrollPane(eventArea), BorderLayout.CENTER);
    }
}
