package org.uml_editor.UMLObject;

import java.awt.*;
import java.util.ArrayList;

public class BasicObjectFactory {
    public static BasicObject createUMLObject(String type, Point position) {
        switch (type) {
            case "class":
                return new ClassShape(position);
            case "use case":
                return new UseCaseShape(position);
            default:
                throw new IllegalArgumentException("Unknown UML object type");
        }
    }

    public static Line createLine(String type, Point startPoint, Point endPoint, Shape startShape, Shape endShape) {
        switch (type) {
            case "composition":
                return new CompositionLine(startPoint, endPoint, startShape, endShape);
            case "association":
                return new AssociationLine(startPoint, endPoint, startShape, endShape);
            case "generalization":
                return new GeneralizationLine(startPoint, endPoint, startShape, endShape);
            default:
                throw new IllegalArgumentException("Unknown line type");
        }
    }

    public static GroupObject createGroup(ArrayList<BasicObject> objects) {
        return new GroupObject(objects);
    }
}
