package seawolf.com.euetilico.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 01/10/2015.
 */
public class ConsumableInfo {
    private String mName;
    private int mBirthPositionCard;
    private boolean mIsLastItem;
    protected static final String NAME_PREFIX = "Name_";

    public ConsumableInfo(int birthPositionCard, boolean isLastItem) {
        mBirthPositionCard = birthPositionCard;
        mIsLastItem = isLastItem;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getBirthPositionCard() {
        return mBirthPositionCard;
    }

    public boolean isLastItem() {
        return mIsLastItem;
    }

    public void setIsLastItem(boolean isLastItem) {
        mIsLastItem = isLastItem;
    }

    public void setBirthPositionCard(int positionCard) {
        mBirthPositionCard = positionCard;
    }
}

