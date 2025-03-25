package org.example;

public enum ItemCondition {
    NEW{
        @Override
        public String toString() {
            return "new";
        }
    },
    USED{
        @Override
        public String toString() {
            return "used";
        }
    },
    DAMAGED{
        @Override
        public String toString() {
            return "damaged";
        }
    }
}
