# What is using port?

Command:

```netstat -vanp tcp | grep 8080```

Explanation:

```-v = verbose```

```-a = all```

```-n = numeric```

```-p = protocol```

Command:

```lsof -i tcp:8080```

Explanation:

```-i = internet address of file```
