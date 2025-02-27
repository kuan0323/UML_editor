package org.uml_editor.UMLAction;

import org.uml_editor.Mode;
import org.uml_editor.UMLFrame.CanvaArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ModeAction extends MouseAdapter {
    protected CanvaArea canvaArea;
    // Singleton instance
    private static SelectMode selectMode;
    private static UseCaseMode useCaseMode;
    private static ClassMode classMode;
    private static AssociationMode associationMode;
    private static CompositionMode compositionMode;
    private static GeneralizationMode generalizationMode;
    protected ModeAction() {
        this.canvaArea = CanvaArea.getInstance();
    }
    // Static method to get instance based on mode
    public static ModeAction getInstance(Mode mode) {
        switch (mode) {
            case SELECT:
                if (selectMode == null) selectMode = new SelectMode();
                return selectMode;
            case USECASE:
                if (useCaseMode == null) useCaseMode = new UseCaseMode();
                return useCaseMode;
            case CLASS:
                if (classMode == null) classMode = new ClassMode();
                return classMode;
            case ASSOCIATION:
                if (associationMode == null) associationMode = new AssociationMode();
                return associationMode;
            case COMPOSITION:
                if (compositionMode == null) compositionMode = new CompositionMode();
                return compositionMode;
            case GENERALIZATION:
                if (generalizationMode == null) generalizationMode = new GeneralizationMode();
                return generalizationMode;
            default:
                return null;
        }
    }
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
}
