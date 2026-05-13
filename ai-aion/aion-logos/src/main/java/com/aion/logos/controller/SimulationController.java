package com.aion.logos.controller;

import com.aion.common.result.Result;
import com.aion.logos.entity.LogosAgent;
import com.aion.logos.service.DebateService;
import com.aion.logos.service.IdentityEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模拟演算控制器 (Simulation Controller)
 * 负责接收外部指令并驱动灵识层进行大规模模拟
 */
@RestController
@RequestMapping("/api/v1/simulation")
@RequiredArgsConstructor
public class SimulationController {

    private final IdentityEngineService identityEngineService;
    private final DebateService debateService;

    /**
     * 初始化 Agent 集群
     *
     * @param count 模拟节点数量
     * @return 初始化的 Agent 列表
     */
    @PostMapping("/init")
    public Result<List<LogosAgent>> initializeSimulation(@RequestParam(defaultValue = "100") int count) {
        List<LogosAgent> agents = identityEngineService.imprintAgents(count);
        return Result.success(agents);
    }

    /**
     * 执行众神议事 (递归辩论)
     *
     * @param topic 演化主题
     * @param agents 参与的节点列表
     * @return 逻辑推演结果
     */
    @PostMapping("/debate")
    public Result<String> executeDebate(@RequestParam String topic, @RequestBody List<LogosAgent> agents) {
        String result = debateService.startAssembly(topic, agents);
        return Result.success(result);
    }
}
