package utils.graphs;

import models.PlayersCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

public class GraphsService extends ApplicationFrame {

    public GraphsService(String title ) {
        super( title );
        setContentPane(createDemoPanel( ));
    }

    private static PieDataset createDataset( ) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        PlayersCollection.getPlayers().get(0).getMapStats().forEach(stat -> dataset.setValue(stat.getMapName(), stat.getWins()));
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset ) {
        return ChartFactory.createPieChart(
                "Победы на разных картах",
                dataset,
                true,
                false,
                false);
    }

    public static JPanel createDemoPanel( ) {
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel( chart );
    }

    public static void main( String[ ] args ) {
        GraphsService demo = new GraphsService( "Mobile Sales" );
        demo.setSize( 1920 , 1080 );
        RefineryUtilities.centerFrameOnScreen( demo );
        demo.setVisible( true );
    }
}

