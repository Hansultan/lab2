package history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//TODO: change List on something which will have only uniq items
public class HistoryBean implements Serializable {
    private List<HistoryNode> nodeList;

    public HistoryBean() {
        nodeList = new ArrayList<>();
    }

    public List<HistoryNode> getNodeList() {
        return nodeList;
    }
}
