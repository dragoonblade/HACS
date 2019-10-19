package HACS.Interfaces;

import HACS.Reminders.NodeVisitor;

public interface Reminder {
    public abstract void accept(NodeVisitor visitor);
}
