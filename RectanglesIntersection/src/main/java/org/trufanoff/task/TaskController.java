package org.trufanoff.task;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class TaskController {

    private TaskFieldPanel taskFieldPanel;

    private Rectangle r1;
    private Rectangle r2;
    private float[] rect;

    public TaskController(TaskFieldPanel taskFieldPanel) {
        this.taskFieldPanel = taskFieldPanel;
    }

    private void createRectanglesFromFile(File file) {
        JSONObject inputObj = null;
        try {
            inputObj = new JSONObject(FileUtils.readFileToString(file, "UTF-8"));
            taskFieldPanel.getInputArea().setText(String.valueOf(inputObj));
        } catch (IOException e) {
            e.printStackTrace();
        }
        r1 = new Rectangle(getCoordinates(inputObj, 0));
        r2 = new Rectangle(getCoordinates(inputObj, 1));
        System.out.println(r1);
        System.out.println(r2);
    }

    private float[] getCoordinates(JSONObject jsonObject, int index) {
        float x1 = jsonObject.getJSONArray("rects").getJSONObject(index).getFloat("x1");
        float x2 = jsonObject.getJSONArray("rects").getJSONObject(index).getFloat("x2");
        float y1 = jsonObject.getJSONArray("rects").getJSONObject(index).getFloat("y1");
        float y2 = jsonObject.getJSONArray("rects").getJSONObject(index).getFloat("y2");
        return new float[]{x1, x2, y1, y2};
    }

    public void findRectangleIntersection(File file) {
        createRectanglesFromFile(file);
        JSONObject outputObj = new JSONObject();
        if (isIntersection()) {
            rect = findRect(r1, r2);
            outputObj.put("rect", new JSONObject());
            outputObj.getJSONObject("rect").put("x1", rect[0]);
            outputObj.getJSONObject("rect").put("x2", rect[1]);
            outputObj.getJSONObject("rect").put("y1", rect[2]);
            outputObj.getJSONObject("rect").put("y2", rect[3]);
        } else {
            outputObj.put("rect", "empty");
        }
        taskFieldPanel.getOutputArea().setText(String.valueOf(outputObj));
    }

    private boolean isIntersection() {
        if (Math.min(r1.getX1(), r1.getX2()) > Math.max(r2.getX2(), r2.getX1())) {
            System.out.println("1 No");
            return false;
        } else if (Math.min(r2.getX1(), r2.getX2()) > Math.max(r1.getX1(), r1.getX2())) {
            System.out.println("2 No");
            return false;
        } else if (Math.max(r1.getY1(), r1.getY2()) < Math.min(r2.getY1(), r2.getY2())) {
            System.out.println("3 No");
            return false;
        } else if (Math.max(r2.getY1(), r2.getY2()) < Math.min(r2.getY1(), r2.getY2())) {
            System.out.println("4 No");
            return false;
        }
        System.out.println("Yes");
        return true;
    }

    private float[] findRect(Rectangle r1, Rectangle r2) {
        float x1 = Math.max(r1.getBottomLeft()[0], r2.getBottomLeft()[0]);
        float y1 = Math.min(r1.getTopRight()[1], r2.getTopRight()[1]);
        float x2 = Math.min(r1.getTopRight()[0], r2.getTopRight()[0]);
        float y2 = Math.max(r1.getBottomLeft()[1], r2.getBottomLeft()[1]);
        return new float[]{x1, x2, y1, y2};
    }

    public void reset() {
        r1 = null;
        r2 = null;
        rect = null;
    }
}
