const StompClient = new StompJs.Client({
    brokerURL : "ws://10.10.0.25:8080/chat-messages-stomp-registry"
});

StompClient.activate();

StompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    StompClient.subscribe('/topic/messages', (messageContent) => {
        const messageParsed = JSON.parse(messageContent.body);
        if(messageParsed.senderId === receiverId && messageParsed.conversationId === convId){
            displayIncomingMessage(messageParsed.content);
        }
    });
};

function displayIncomingMessage(message) {
    const chatBox = document.getElementById('chat-items');
    const classList = 'chat-item-container chat-item-container--his';

    const div =       document.createElement('div');
    const nestedDiv = document.createElement('div');

    nestedDiv.innerText = message;
    div.append(nestedDiv);

    div.classList = classList;
    chatBox.prepend(div);
}

StompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

StompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function sendMessage(senderId, content, conversationId) {
    StompClient.publish({
        destination: "/chat/message",
        body: JSON.stringify({'content': content, senderId : senderId, conversationId : conversationId})
    });
    displaySentMessage(content);
}

function displaySentMessage(message){
    const chatBox = document.getElementById('chat-items');
    const classList = 'chat-item-container chat-item-container--mine';
    const div = document.createElement('div');
    const nestedDiv = document.createElement('div');
    nestedDiv.innerText = message;
    div.append(nestedDiv);
    div.classList = classList;
    chatBox.prepend(div);
}