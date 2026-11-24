package sr.beauti;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Path;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import javafx.stage.FileChooser;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import beast.base.evolution.alignment.Taxon;
import sr.evolution.sranges.StratigraphicRange;

import sr.util.Tools;

public class SRExport {

    public enum SRImportFileFormat {
      ONECOL, MULTICOL
    };

    private String exportFileName = "sranges.txt";

    void exportFile (List<StratigraphicRange> sranges) throws Exception {
        String userHome = System.getProperty("user.home");
        Path homePath = Path.of(userHome);
        Path exportFile = homePath.resolve(exportFileName );

        if (Files.exists(exportFile)) {

            // show yes/no popup
            Alert alert = new Alert( Alert.AlertType.CONFIRMATION, "Export file already exist, overwrite existing file ?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirm Action");
            alert.setHeaderText(null); // Removes the default "Confirmation" header box
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                Files.deleteIfExists(exportFile);
            } else {
                return;
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(exportFile, StandardCharsets.UTF_8)) {

            // header row
            //writer.write("name\tfirst\tlast");
            //writer.newLine();

            for (StratigraphicRange sr: sranges) {
                writer.write(sr.getID() + "\t" + sr.getFirstOccurrenceID() + "\t" + sr.getLastOccurrenceID());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
