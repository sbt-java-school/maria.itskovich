
����������� ��������� ExecutionManager
 
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
 
����� execute ��������� ������ ������, ��� ������� ������� ExecutionManager ������ ��������� �����������. ����� ���������� ���� ������ ������ ����������� callback (����� 1 ���). 
 
����� execute � ��� ������������� �����, ������� ����� ���������� ������ Context. Context ��� ��������� ���������� ����:
 
public interface Context {
    int getCompletedTaskCount();
 
    int getFailedTaskCount();
 
    int getInterruptedTaskCount();
 
    void interrupt();
 
    boolean isFinished();
}
 
����� getCompletedTaskCount() ���������� ���������� ������, ������� �� ������� ������ ������� �����������.
����� getFailedTaskCount() ���������� ���������� ������, ��� ���������� ������� ��������� Exception.
����� interrupt() �������� ���������� ������, ������� ��� �� ������ �����������.
����� getInterruptedTaskCount() ���������� ���������� ������, ������� �� ���� �������� ��-�� ������ (������� ����������� ������).
����� isFinished() ������ true, ���� ��� ����� ���� ��������� ��� ��������, false � ��������� ������.  
