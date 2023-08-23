const StompClient = new StompJs.Client({
    brokerURL : "ws://localhost:8080/chat-messages-stomp-registry"
});

console.log(receiverId);


StompClient.activate();

StompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    StompClient.subscribe('/topic/messages', (messageContent) => {
        const messageParsed = JSON.parse(messageContent.body);
        //console.log("Message body: " + messageParsed);
        //console.log("Sender Id: " + messageParsed.senderId + ", Receiver id: " + receiverId);
        if(messageParsed.senderId == receiverId){
            console.log("Received: " + messageParsed.content);
        }
    });
};

StompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

StompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function sendMessage(senderId, content) {
    StompClient.publish({
        destination: "/chat/message",
        body: JSON.stringify({'content': content, senderId : senderId})
    });
}