//package sr.beauti;
//package beastfx.app.inputeditor;
package beastfx.app.beauti;





import java.util.Arrays;
import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import beastfx.app.inputeditor.BEASTObjectDialog;
import beastfx.app.inputeditor.BeautiDoc;
import beastfx.app.inputeditor.BeautiSubTemplate;
import beastfx.app.inputeditor.InputEditor;
import beastfx.app.inputeditor.ListInputEditor;
import beastfx.app.util.FXUtils;
import beast.base.core.BEASTInterface;
import beast.base.core.Function;
import beast.base.core.Function.Constant;
import beast.base.core.Input;
import beast.base.inference.Distribution;
import beast.base.inference.distribution.Dirichlet;
import beast.base.inference.distribution.ParametricDistribution;
import beast.base.inference.distribution.Prior;
import beast.base.inference.parameter.IntegerParameter;
import beast.base.inference.parameter.RealParameter;
import beast.base.parser.PartitionContext;
import beast.base.evolution.tree.MRCAPrior;
import sr.math.distributions.SRMRCAPrior;
import sr.beauti.SRMRCAPriorProvider;
import beastfx.app.beauti.PriorInputEditor;

public class SRPriorInputEditor extends PriorInputEditor {

	public SRPriorInputEditor(BeautiDoc doc) {
		super(doc);
	}

	public SRPriorInputEditor() {
		super();
	}

	@Override
	public Class<?> type() {
		return SRMRCAPrior.class;
	}

	@Override
	public void init(Input<?> input, BEASTInterface beastObject, int listItemNr, ExpandOption isExpandOption, boolean addButtons) {
        m_bAddButtons = addButtons;
        m_input = input;
        m_beastObject = beastObject;
        this.itemNr= listItemNr;

        pane = FXUtils.newVBox();
        HBox itemBox = FXUtils.newHBox();

        String text = "Stratigraphic Ranges";

        Label label = new Label(text);
        Font font = label.getFont();
        Dimension2D size = new Dimension2D(font.getSize() * 200 / 13, font.getSize() * 25/13);
        label.setMinSize(size.getWidth(), size.getHeight());
        label.setPrefSize(size.getWidth(), size.getHeight());
        String tipText = getDoc().tipTextMap.get(beastObject.getID());
        //label.setTooltip(new Tooltip();
        itemBox.getChildren().add(label);

        Button editRangeButton = new Button("Edit");
        editRangeButton.setOnAction(e -> {
            SRMRCAPriorProvider provider = new SRMRCAPriorProvider();
            List<Distribution> li = provider.createDistribution(doc);
        });
        //editRangeButton.setPrefWidth();
        itemBox.getChildren().add(editRangeButton);

        /*
        if (tipText != null) {
            Label tipTextLabel = new Label(" " + tipText);
            itemBox.getChildren().add(tipTextLabel);
        }
        */
        
        pane.getChildren().add(itemBox);
        getChildren().add(pane);
	}
}	
