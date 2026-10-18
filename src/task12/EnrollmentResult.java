package task12;

public sealed interface EnrollmentResult permits Accepted, Rejected, WaitListed, PendingApproval {}
