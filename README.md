# DDES
1- I remove the Vector queue from Channels and use PriorityQueue
2- I use shutdown message which send shutdown messages to all out channels and then send PoisonPill message to getSelf
