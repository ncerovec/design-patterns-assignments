/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.mvc;

import java.util.List;
import ncerovec_zadaca_3.AppHelper.LimitedQueue;

/**
 * MVC - AbstractView
 * @author nino
 */
public abstract class ConsoleView implements View
{
    private Controller controller = null;
    
    private final String ANSI_ESC = "\033";
    private final int maxRows = 40;
    private final int maxCols = 160;

    private int color = 0;
    private int cursor[] = {0,0};

    private int numRows = 40;    
    private int numCols = 80;
    private int numRowsBuffer = 40;
    
    private int currentPage = 0;
    private LimitedQueue<String> rowBuffer = null;
    
    public ConsoleView(int numRows, int numCols, int numRowsBuffer)
    {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numRowsBuffer = numRowsBuffer;
        
        this.rowBuffer = new LimitedQueue(numRowsBuffer);
    }

    public Controller getController() { return controller; }
    public void setController(Controller controller) { this.controller = controller; }

    protected void setCursor(int x, int y)
    {
        this.cursor[0] = x;
        this.cursor[1] = y;
        setCursorPosition(x, y);
    }
    
    protected int getCursorX() { return this.cursor[0]; }
    protected int getCursorY() { return this.cursor[1]; }
    
    protected int getNumRows() { return numRows; }
    protected void setNumRows(int numRows) { this.numRows = numRows; }

    protected int getNumCols() { return numCols; }
    protected void setNumCols(int numCols) { this.numCols = numCols; }

    protected int getNumRowsBuffer() { return numRowsBuffer; }
    protected void setNumRowsBuffer(int numRowsBuffer) { this.numRowsBuffer = numRowsBuffer; }
    
    protected int getTotalPages() { return (this.rowBuffer.size()/this.numRows+1); }
    
    public void setViewConfig(int numRows, int numCols, int numRowsBuffer)
    {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numRowsBuffer = numRowsBuffer;
    }
    
    protected void previousConsole()
    {
        if(this.currentPage > 1) this.currentPage--;
        printBuffer(this.currentPage);
    }
    
    protected void nextConsole()
    {
        if(this.currentPage < getTotalPages()) this.currentPage++;
        printBuffer(this.currentPage);
    }
        
    protected void printConsole(String text)
    {       
        String[] textRows = text.trim().split("\n");
        
        //this.rowBuffer.addAll(Arrays.asList(textRows));           //downwards print (BUG: removing elements - IndexOutOfRange)
        for(String row : textRows) this.rowBuffer.add(row);         //downwards print
        //for(String row : textRows) this.rowBuffer.addFirst(row);  //upwards print
        
        /*
        for(int i=0; i<textRows.length; i++)
        {
            String row = textRows[i];
            print(x+i, y, color, row);
        }
        */
        
        printBuffer();
    }
       
    private void printBuffer()
    {
        this.currentPage = getTotalPages();
        printBuffer(this.currentPage);
    }
    
    private void printBuffer(int page)
    {
        int totalPages = getTotalPages();
        //int startIndex = (page > 0 && totalPages >= page) ? (this.numRows*(page-1)) : (this.numRows*(totalPages-1));
        int endIndex = (page > 0 && this.rowBuffer.size() >= (this.numRows*page)) ? (this.numRows*page) : this.rowBuffer.size();
        int startIndex = (endIndex-this.numRows > 0) ? endIndex-this.numRows : 0;
        List<String> bufferPage = this.rowBuffer.subList(startIndex, endIndex);
        
        int x = this.numRows-bufferPage.size();
        int y = (this.maxCols-this.numCols)/2;
        
        clearConsole();
        setCursor(x, y);
        printPageDownwards(bufferPage);
        //printPageUpwards(bufferPage);
        //setCursorHome();
    }
    
    private void printPageUpwards(List<String> bufferPage)
    {
        int i=bufferPage.size();
        for(String row : bufferPage) { print(getCursorX()+(--i), getCursorY(), this.color, row); }
    }
    
    private void printPageDownwards(List<String> bufferPage)
    {
        int i=0;
        for(String row : bufferPage) { print(getCursorX()+(i++), getCursorY(), this.color, row); }
    }
    
    @Override
    public void showMessage(String message)
    {
        print(0, 0, this.color, message);
    }
    
    @Override
    public void requestAction(String message)
    {
        String action = this.getInput(message);
        this.getController().actionPerformed(action);
    }
    
    //console controls and print
    
    private void print(int x, int y, int color, String text)
    {
        initPrint(x, y, color);
        if(text.length() > this.numCols) text = text.substring(0, text.length()-3) + "...";
        
        System.out.print(text);
        //try { Thread.sleep(100); } catch (InterruptedException ex) { }
    }
    
    private String getInput(String message)
    {
        print(this.numRows, 0, this.color, message);
        String input = System.console().readLine();
        clearLine();
        return input;
    }
    
    private void initPrint(int x, int y, int color)
    {
        setCursorPosition(x, y);
        setColor(color);
    }
    
    private void setColor(int color)
    {
        this.color = color;
        controlConsole("[" + color + "m");
    }
    
    private void setCursorPosition(int x, int y) { controlConsole("[" + x + ";" + y + "f"); }
    
    private void setCursorHome() { controlConsole("[H"); }
    
    private void clearLine() { controlConsole("[2K"); };
    
    private void clearConsole() { controlConsole("[2J"); }
    
    private void controlConsole(String control) { System.out.print(ANSI_ESC + control); }
}
