package design_mode.command_mode;

interface Command
{
    public void execute();
}

class Tv
{
	public void turnOn()
	{
	    System.out.println("The televisino is on.");
	}
	
	public void turnOff()
	{
	    System.out.println("The television is off.");
	}
	
	public void changeChannel(int channel)
	{
        currentChannel = channel;
		System.out.println("Now TV channel is " + channel);
	}

    private int currentChannel = 0;
}

class ChannelCommand implements Command
{
	private Tv myTv;
    private int channel;
	
    public ChannelCommand(Tv tv, int channel)
    {
        myTv = tv;
        this.channel = channel;
    }

	public void execute()
    {
		myTv.changeChannel(channel);
	}
}

class OnCommand implements Command
{
	private Tv myTv;

	public OnCommand (Tv tv)
    {
		myTv = tv;
	}
	public void execute( )
    {
		myTv.turnOn();
	}
}

class OffCommand implements Command
{
	private Tv myTv;
	
	public OffCommand (Tv tv)
    {
		myTv  =  tv;
	}
	public void execute()
    {
		myTv.turnOff();
	}
}

class Control
{    

    private Command onCommand, offCommand, changeChannel;

	public Control(Command on, Command off, Command channel)
    {
		onCommand = on;
		offCommand = off;
        changeChannel = channel;
	}

	public void turnOn()
    {
		onCommand.execute() ;
	}

	public void turnOff()
    {
		offCommand.execute();
	}

    public void changeChannel()
    {
    	changeChannel.execute();
    }
}

public class Master{

	public static void main(String[] args)
	{
        Tv  myTv = new Tv();
	    OnCommand on = new OnCommand(myTv);
	    OffCommand off = new OffCommand(myTv);
	    ChannelCommand channel = new ChannelCommand(myTv, 2);
	    Control control = new Control( on, off, channel);
        control.turnOn();
        control.changeChannel();
        control.turnOff();
	}
}