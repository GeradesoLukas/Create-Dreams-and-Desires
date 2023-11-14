package uwu.lopyluna.create_dd.block.BlockProperties.wood;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;


@SuppressWarnings({"unused", "deprecation"})
public class CanBurnBlockStairs extends StairBlock {

    public CanBurnBlockStairs(BlockState pBaseState, Properties pProperties) {
        super(pBaseState, pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 10;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 3;
    }
}
