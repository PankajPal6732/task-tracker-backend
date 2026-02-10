package com.example.demo.enumIdentifier;

public enum Status {
	  TODO(1),
	    IN_PROGRESS(2),
	    DONE(3);

	    private final int order;

	    Status(int order) {
	        this.order = order;
	    }

	    public int getOrder() {
	        return order;
	    }

	    /**
	     * Validate allowed status transition
	     */
	    public boolean canTransitionTo(Status nextStatus) {
	        // Same status is allowed
	        if (this == nextStatus) {
	            return true;
	        }

	        // Only allow moving to the immediate next status
	        return nextStatus.order == this.order + 1;
	    }
}
