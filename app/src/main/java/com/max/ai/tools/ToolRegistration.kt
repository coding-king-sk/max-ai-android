package com.max.ai.tools
import com.max.ai.core.agent.ToolRegistry
fun ToolRegistry.registerAllTools() { val a = getAll(); registerAll(*a.toTypedArray()) }