# build and uninstall tools
https://pdos.csail.mit.edu/6.S081/2021/tools.html

## vscode debug

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "debug xv6",
            "type": "cppdbg",
            "request": "launch",
            "program": "${workspaceFolder}/kernel/kernel",
            "args": [],
            "stopAtEntry": true,
            "cwd": "${workspaceFolder}",
            "miDebuggerServerAddress": "localhost:25000",
            "miDebuggerPath": "/usr/bin/gdb-multiarch",
            "environment": [],
            "externalConsole": false,
            "MIMode": "gdb",
            "setupCommands": [
                {
                    "description": "pretty printing",
                    "text": "-enable-pretty-printing",
                    "ignoreFailures": true
                }
            ],
            "logging": {
                "engineLogging": true,
                "programOutput": true,
            }
        }
    ]
}
```

## debug user defined function

continue, when stoped, and then execute '-exec file user/_functionName' in debug console