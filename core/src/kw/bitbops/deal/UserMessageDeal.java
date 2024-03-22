package kw.bitbops.deal;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;

import java.util.LinkedList;
import java.util.Queue;

import kw.bitbops.GameLogic;
import kw.bitbops.bean.DingBean;
import kw.bitbops.bean.RoomInfo;
import kw.bitbops.bean.UserInfo;
import kw.bitbops.bean.UserMessageInfo;
import kw.bitbops.message.CreateRoomMessage;
import kw.bitbops.message.EnterMessage;
import kw.bitbops.message.ExitMessage;
import kw.bitbops.message.HelloMessage;
import kw.bitbops.message.HitDingMessage;
import kw.bitbops.message.OutDingMessage;
import kw.bitbops.message.RoomListMessage;

public class UserMessageDeal<BaseMessage> extends BaseMessageDeal<BaseMessage>{
    private ArrayMap<String,RoomInfo> roomMap;
    private Queue<UserInfo> userInfoBeanArray;
    private GameLogic logic;
    private int userId = 0;

    public UserMessageDeal(){
        this.userInfoBeanArray = new LinkedList<>();


        this.roomMap = new ArrayMap<>();
        this.logic = new GameLogic();
    }

    @Override
    public void deal(BaseMessage baseMessage, UserMessageInfo userMessageInfo) {
        super.deal(baseMessage, userMessageInfo);
        if (baseMessage instanceof EnterMessage){
            dealEnterMessage(userMessageInfo, (EnterMessage) baseMessage);

        }else if (baseMessage instanceof ExitMessage){
            dealExitMessage((ExitMessage) baseMessage);

        }else if (baseMessage instanceof HitDingMessage){
            dealHitDingMessage((HitDingMessage)baseMessage);

        }else if (baseMessage instanceof OutDingMessage){
            dealOutDingMessage((OutDingMessage)baseMessage);

        }else if (baseMessage instanceof CreateRoomMessage){
            dealCreateRoom((CreateRoomMessage) baseMessage,userMessageInfo.getConnection());
        }
    }

    private void dealCreateRoom(CreateRoomMessage object, Connection connection) {
        RoomInfo info = new RoomInfo();
        info.setRoomName(object.getName());
        info.setAdmin(connection.getID());
        roomMap.put(object.getId(),info);
        sendUDP(connection,info);

        RoomListMessage roomListMessage = new RoomListMessage();
        for (ObjectMap.Entry<String, RoomInfo> stringRoomInfoEntry : roomMap) {
            RoomInfo value = stringRoomInfoEntry.value;
            roomListMessage.addRoomInfo(value);
        }
        for (UserInfo userMessageInfo : userInfoBeanArray) {
            Connection connection1 = userMessageInfo.getConnection();
            System.out.println("fasong liebiao");
            sendUDP(connection1,roomListMessage);
        }
    }

    private void dealOutDingMessage(OutDingMessage object) {
        System.out.println("user out ding ---------");
        DingBean dingBean = logic.outDing();
        object.setId(dingBean.getDingId());
        object.setPox(dingBean.getPox());
        object.setPoy(dingBean.getPoY());
        for (UserInfo info : userInfoBeanArray) {
            sendUDP(info.getConnection(),object);
        }
    }

    private void dealHitDingMessage(HitDingMessage object) {
        System.out.println("user hit ding ---------");
        logic.hitDing();
        for (UserInfo info : userInfoBeanArray) {
            sendUDP(info.getConnection(),object);
        }
    }

    private void dealExitMessage(ExitMessage object) {
        ExitMessage exitMessage = object;
        int id = exitMessage.getId();
        UserInfo info = null;
        for (UserInfo userInfo : userInfoBeanArray) {
            int id1 = userInfo.getId();
            if(id == id1){
                info = userInfo;
                break;
            }
        }
        userInfoBeanArray.remove(info);
    }

    private void dealEnterMessage(UserMessageInfo userMessageInfo, EnterMessage object) {
        EnterMessage enterMessage = object;
        enterMessage.setId(userId++);
        userInfoBeanArray.add(new UserInfo(userMessageInfo.getConnection(),enterMessage.getId()));
        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setId(enterMessage.getId());
        helloMessage.setMsg("你的序号为："+helloMessage.getId());
        sendUDP(userMessageInfo.getConnection(),helloMessage);
    }

    private void sendUDP(Connection connection, Object helloMessage) {

    }

}
