package lua.debug;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SerializationHelper {

	public static byte[] serialize(Serializable object) throws IOException {
    	ByteArrayOutputStream bout = new ByteArrayOutputStream();
    	DataOutputStream dout = new DataOutputStream(bout);
    	
    	serialize(object, dout);
    	
    	byte[] data = bout.toByteArray();
    	
    	bout.close();
    	dout.close();
    	
    	return data;
    }    
    
    public static Serializable deserialize(byte[] data) throws IOException {
    	ByteArrayInputStream bin = new ByteArrayInputStream(data);
    	DataInputStream din = new DataInputStream(bin);
    	
    	Serializable object = deserialize(din);
    	
    	bin.close();
    	din.close();
    	
    	return object;
    }

    
    static final int SERIAL_TYPE_NullableString = 0;
    static final int SERIAL_TYPE_TableVariable = 1;
    static final int SERIAL_TYPE_Variable = 2;
    static final int SERIAL_TYPE_DebugResponseCallgraph = 3;
	static final int SERIAL_TYPE_DebugResponseStack = 4;
	static final int SERIAL_TYPE_DebugResponseSimple = 5;
	static final int SERIAL_TYPE_DebugSupportState = 6;
	static final int SERIAL_TYPE_StackFrame = 7;
	static final int SERIAL_TYPE_DebugRequestType = 8;
	static final int SERIAL_TYPE_DebugRequest = 9;
	static final int SERIAL_TYPE_DebugRequestStack = 10;
	static final int SERIAL_TYPE_DebugRequestLineBreakpointToggle = 11;
	static final int SERIAL_TYPE_DebugEventType = 12;
	static final int SERIAL_TYPE_DebugEvent = 13;
	static final int SERIAL_TYPE_DebugEventBreakpoint = 14;
	
	public static void serialize(Serializable object, DataOutputStream dout)
	throws IOException {
		if (object instanceof NullableString) {
			dout.writeInt(SERIAL_TYPE_NullableString);
			NullableString.serialize(dout, (NullableString)object); 
		} else if (object instanceof TableVariable) {
			dout.writeInt(SERIAL_TYPE_TableVariable);
			TableVariable.serialize(dout, (TableVariable)object);    		
		} else if (object instanceof Variable) {
			dout.writeInt(SERIAL_TYPE_Variable);
			Variable.serialize(dout, (Variable)object);
		} else if (object instanceof StackFrame) {
			dout.writeInt(SERIAL_TYPE_StackFrame);
			StackFrame.serialize(dout, (StackFrame)object);
		} else if (object instanceof DebugResponseSimple) {
			dout.writeInt(SERIAL_TYPE_DebugResponseSimple);
			DebugResponseSimple.serialize(dout, (DebugResponseSimple)object);
		} else if (object instanceof DebugResponseStack) {
			dout.writeInt(SERIAL_TYPE_DebugResponseStack);
			DebugResponseStack.serialize(dout, (DebugResponseStack)object);
		} else if (object instanceof DebugResponseCallgraph) {
			dout.writeInt(SERIAL_TYPE_DebugResponseCallgraph);
			DebugResponseCallgraph.serialize(dout, (DebugResponseCallgraph)object);
		} else if (object instanceof DebugSupport.State) {
			dout.writeInt(SERIAL_TYPE_DebugSupportState);
			DebugSupport.State.serialize(dout, (DebugSupport.State)object);
		} else if (object instanceof DebugRequestType) {
			dout.writeInt(SERIAL_TYPE_DebugRequestType);
			DebugRequestType.serialize(dout, (DebugRequestType)object);
		} else if (object instanceof DebugRequestStack) {
			dout.writeInt(SERIAL_TYPE_DebugRequestStack);
			DebugRequestStack.serialize(dout, (DebugRequestStack)object);
		} else if (object instanceof DebugRequestLineBreakpointToggle) {
			dout.writeInt(SERIAL_TYPE_DebugRequestLineBreakpointToggle);
			DebugRequestLineBreakpointToggle.serialize(dout, (DebugRequestLineBreakpointToggle)object);
		} else if (object instanceof DebugRequest) {
			dout.writeInt(SERIAL_TYPE_DebugRequest);
			DebugRequest.serialize(dout, (DebugRequest)object);
		} else if (object instanceof DebugEventType) {
			dout.writeInt(SERIAL_TYPE_DebugEventType);
			DebugEventType.serialize(dout, (DebugEventType)object);
		} else if (object instanceof DebugEventBreakpoint) {
			dout.writeInt(SERIAL_TYPE_DebugEventBreakpoint);
			DebugEventBreakpoint.serialize(dout, (DebugEventBreakpoint)object);
		} else if (object instanceof DebugEvent) {
			dout.writeInt(SERIAL_TYPE_DebugEvent);
			DebugEvent.serialize(dout, (DebugEvent)object);
		} else {
			// catch the errors: forgot to implement serialization/deserialization
			throw new RuntimeException("serialization operation is not supported");
		}
	}
	
	public static Serializable deserialize(DataInputStream din)
			throws IOException {
		Serializable object = null;
    	int type = din.readInt();
    	switch (type) {
    	case SERIAL_TYPE_NullableString:
    		object = NullableString.deserialize(din);
    		break;
    	case SERIAL_TYPE_TableVariable:
    		object = TableVariable.deserialize(din);
    		break;
    	case SERIAL_TYPE_Variable:
    		object = Variable.deserialize(din);
    		break;
    	case SERIAL_TYPE_StackFrame:
    		object = StackFrame.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugResponseSimple:
    		object = DebugResponseSimple.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugResponseCallgraph:
    		object = DebugResponseCallgraph.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugResponseStack:
    		object = DebugResponseStack.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugSupportState:
    		object = DebugSupport.State.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugRequestType:
    		object = DebugRequestType.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugRequestStack:
    		object = DebugRequestStack.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugRequestLineBreakpointToggle:
    		object = DebugRequestLineBreakpointToggle.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugRequest:
    		object = DebugRequest.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugEventType:
    		object = DebugEventType.deserialize(din);
    		break;
    	case SERIAL_TYPE_DebugEventBreakpoint:
    		object = DebugEventBreakpoint.deserialize(din);
    		break;    		
    	case SERIAL_TYPE_DebugEvent:
    		object = DebugEvent.deserialize(din);
    		break;
    	default:
    		throw new RuntimeException("deserialization operation is not supported");	
    	}

    	return object;
	}    
}
